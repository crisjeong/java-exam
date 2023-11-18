package com.example.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManagementUDPServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Map<String, Channel> sessions = new ConcurrentHashMap<>();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new SessionManagementUDPServerHandler(sessions));

            bootstrap.option(ChannelOption.SO_BROADCAST, true);

            Channel channel = bootstrap.bind(8888).sync().channel();

            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
