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
import java.util.concurrent.TimeoutException;
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
    }}

/****************************************** */
void CheckNodeAlive(ElementManagerInterface* pElementManager, ElementNode* pNode)
{
    if (pNode)
    {
        ConnectionState state = pNode->GetConnectionState();

        /* deprecated : 070405 */
//		CTimeSpan span = CTime::GetCurrentTime() - pNode->GetRecentStatusTime();
//        int spanMillis = span.GetTotalSeconds() * 1000; // ��� �ð� �и���

        /* added : 070405 */
        DWORD currTick = GetTickCount();
        DWORD lastTick = pNode->GetRecentStatusTick();
        int spanMillis = currTick - lastTick; /* millis : must be {int} type */
        
        /* 080221.John : GetTickCount()�� 49.7 days wrap�Ǹ� ���̻� CheckAlive ������ ������ �ʴ� ���� ���� */ 
        if (spanMillis < 0) 
        {
            spanMillis = 0;
            pNode->ResetRecentStatusTick();
        }

        switch (state)
        {
        case CONNSTATE_NORMAL:
            if (spanMillis > g_DisconConfirmTO)	// �̺�Ʈ �̼��� 1�� �ʰ��� RTRV-EMS ���� 
            {
                if (pNode->IsConnected()) // �̹� disconnected�� �Ǵܵ� ��忡 ���� �������� ���� �ʵ��� 
                {
                    // NORMAL -> DOUBT 
                    pNode->SetConnectionState(CONNSTATE_CONFIRM);
                    
                    /* 121022.John : ��� Ÿ�Ժ� DISCON CMD */ 	
                    CString strConfirmCmd = GetDisconConfirmCmd(pNode->GetNodeTypeID());

                    // send RTRV-EMS
                    if (!strConfirmCmd.IsEmpty())
                    {
                        if (Iris3elem_SendCommand(pNode, strConfirmCmd))
                        {
                            /* 080201.John : last sent command */ 
                            pNode->SetSentCmd(strConfirmCmd);	
                        }
                    }
                }
            }
            break;

        case CONNSTATE_CONFIRM:
            if (spanMillis > g_DisconTO)	// �̺�Ʈ �̼��� 2�� �ʰ��� ������ �Ǵ� 
            {
                if (pNode->IsConnected())	// �̹� disconnected�� �Ǵܵ� ��忡 ���� �������� ���� �ʵ��� 
                {
                    // CONFIRM -> DISCONN                                 
                    pNode->SetConnectionState(CONNSTATE_DISCONN);

                    /* 080201.John : last sent command */ 
                    pNode->SetSentCmd("");	

                    // report disonn event 
                    if (pElementManager)
                        pElementManager->ReportConnectionStatus(pNode);
                }
            }
            else if (spanMillis > g_DisconConfirmTO+(g_DisconTO-g_DisconConfirmTO)/2) // �̺�Ʈ �̼��� 1�� 30�� �ʰ��� RTRV-EMS 1���� ���� 
            {
                if (pNode->IsConnected())	// �̹� disconnected�� �Ǵܵ� ��忡 ���� �������� ���� �ʵ��� 
                {
                    /* 121022.John : ��� Ÿ�Ժ� DISCON CMD */ 	
                    CString strConfirmCmd = GetDisconConfirmCmd(pNode->GetNodeTypeID());

                    // send RTRV-EMS : 1 more time 
                    if (!strConfirmCmd.IsEmpty())
                    {
                        if (Iris3elem_SendCommand(pNode, strConfirmCmd))
                        {
                            /* 080201.John : last sent command */
                            pNode->SetSentCmd(strConfirmCmd);	 	
                        }
                    }
                }
            }
            break;

        case CONNSTATE_DISCONN: // ������ 1�и��� RTRV-SYS ����
            {
//                 int spanSec = spanMillis / 1000; /* ms -> sec */
//                 if ((spanSec % g_DisconPingInterval) == 0) /* 1�� ������ RTRV-SYS;�� �����Ѵ�. */

                if (spanMillis >= g_DisconPingInterval*1000)
                {
                    /* 121022.John : ��� Ÿ�Ժ� DISCON CMD */ 	
                    CString strPingCmd = GetDisconPingCmd(pNode->GetNodeTypeID());
                    
                    // send RTRV-SYS
                    if (!strPingCmd.IsEmpty())
                    {
                        if (Iris3elem_SendCommand(pNode, strPingCmd))
                        {
                            /* 080201.John : last sent command */ 
                            pNode->SetSentCmd(strPingCmd);	
                        }
                        
                        // pNode->ResetRecentCmdSentTick(); // SendCommand() ���������� ȣ��ȴ�. 
                        pNode->ResetRecentStatusTick();		/* 080125.John : spanMillis �� Status ���� �ð��̹Ƿ� ���⼭ �ʱ�ȭ ���־�� �Ѵ�. */ 
                    }
                }
            }
            break;

        default:
            break;
        }
    }
}

/** ���������� LED-INFO�� �޾Ҵ� tick�� ����. */
DWORD ElementNode::GetRecentStatusTick()
{
    return m_dwRecentStatusTick;
}

/** �ֱ� LED-INFO ���� TICK �ʱ�ȭ. */
void ElementNode::ResetRecentStatusTick()
{
    /* 1�� ~ 11�ʱ��� */ 

/*	080125.John : �ּ�ó���� -- 10 �̳����� random�ϰ� �����ǹǷ� CHECK_TMR���� ���� �Ǵ��ϱⰡ ����ϴ�. 

    unsigned long  tick, timeout; 
    timeout = 10 + ((unsigned long)rand() % 50);   / * �� 50�� ���� * /
    tick = GetTickCount() + (timeout * 100);

    m_dwRecentStatusTick = tick;
*/

	m_dwRecentStatusTick = GetTickCount();

}

##########
#