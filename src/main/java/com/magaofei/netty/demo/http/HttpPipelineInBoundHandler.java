package com.magaofei.netty.demo.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

public class HttpPipelineInBoundHandler extends SimpleChannelInboundHandler<Channel> {
//    @Override
//    protected void initChannel(Channel ch) throws Exception {
//        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpResponseEncoder());
//        pipeline.addLast("codec", new HttpServerCodec());
//        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Channel msg) throws Exception {

        System.out.println("Server received: " + msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel read complete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(future -> {}).sync();
//        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception caught: " + cause.getMessage());
        cause.printStackTrace();
//        super.exceptionCaught(ctx, cause);
    }
}
