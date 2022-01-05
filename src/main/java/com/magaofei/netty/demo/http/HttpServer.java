package com.magaofei.netty.demo.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpServer {

    public void start() throws InterruptedException {

        InBoundHandler inBoundHandler = new InBoundHandler();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.localAddress(8080);

        EventLoopGroup group = new NioEventLoopGroup();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new httpPipelineInitialzer());
//                .childHandler(new ChannelInitializer<Channel>() {
//                    @Override
//                    protected void initChannel(Channel ch) throws Exception {
//                        ch.pipeline().addLast(new HttpPipelineInBoundHandler());
//                    }
//                });
        ChannelFuture f = serverBootstrap.bind().sync();
        f.channel().closeFuture().sync();
    }
    public static void main(String[] args) throws InterruptedException {
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }
}
