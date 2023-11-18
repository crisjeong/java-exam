package com.example.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.util.Map;

class SessionManagementUDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final AttributeKey<String> SESSION_ID = AttributeKey.valueOf("sessionId");

    private final Map<String, Channel> sessions;

    public SessionManagementUDPServerHandler(Map<String, Channel> sessions) {
        this.sessions = sessions;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        // Assuming TLV structure: [Type(1 byte)][Length(2 bytes)][Value(variable length)]
        ByteBuf content = packet.content();
        byte type = content.readByte();
        int length = content.readShort();

        // Check if there is enough readable bytes to read the value
        if (content.readableBytes() < length) {
            System.out.println("Incomplete message received");
            return;
        }

        byte[] valueBytes = new byte[length];
        content.readBytes(valueBytes);
        String value = new String(valueBytes);

        // Handle the message based on the type and value
        System.out.println("Received TLV message - Type: " + type + ", Value: " + value);

        // Manage session
        String sessionId = getSessionId(ctx.channel());
        if (sessionId == null) {
            sessionId = createSession(ctx.channel());
            System.out.println("New session created: " + sessionId);
        }

        // Respond to the client if needed
        // ...

        // Update session-related information
        // ...
    }

    private String getSessionId(Channel channel) {
        Attribute<String> attribute = channel.attr(SESSION_ID);
        return attribute.get();
    }

    private String createSession(Channel channel) {
        String sessionId = generateSessionId();
        Attribute<String> attribute = channel.attr(SESSION_ID);
        attribute.set(sessionId);
        sessions.put(sessionId, channel);
        return sessionId;
    }

    private String generateSessionId() {
        // This is a simple example; you may want to use a more robust method to generate session IDs.
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // Channel is closed, remove it from the session
        String sessionId = getSessionId(ctx.channel());
        if (sessionId != null) {
            sessions.remove(sessionId);
            System.out.println("Session closed: " + sessionId);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Handle exceptions
        cause.printStackTrace();
        ctx.close();
    }
}
