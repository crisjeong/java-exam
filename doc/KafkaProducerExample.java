import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class KafkaProducerExample {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerExample(String bootstrapServers, String topic) {
        // Set producer properties
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create a Kafka producer factory
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);

        // Create a KafkaTemplate with the producer factory
        this.kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    public void produceMessage(String key, String value, Consumer<SendResult<String, String>> onSuccess,
            Consumer<Throwable> onFailure) {
        // Create a ProducerRecord with the specified topic, key, and value
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("your_topic", key, value);

        // Add a callback to handle success or failure
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                onSuccess.accept(result);
            }

            @Override
            public void onFailure(Throwable ex) {
                onFailure.accept(ex);

                if (ex instanceof TimeoutException) {
                    /* TODO */
                }
            }
        });
    }

    public static Throwable findCauseUsingPlainJava(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    public static void main(String[] args) {
        // Replace "localhost:9092" with your Kafka broker address
        String bootstrapServers = "localhost:9092";
        String topic = "your_topic";

        KafkaProducerExample kafkaProducer = new KafkaProducerExample(bootstrapServers, topic);

        // Replace "your_key" and "your_value" with the key and value you want to send
        kafkaProducer.produceMessage("your_key", "your_value",
                result -> {
                    System.out.printf("Message sent successfully! Topic: %s, Partition: %s, Offset: %s%n",
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                },
                ex -> {
                    System.err.println("Error sending message: " + ex.getMessage());
                });
    }
}