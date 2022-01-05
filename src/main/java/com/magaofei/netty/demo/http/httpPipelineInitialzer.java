package com.magaofei.netty.demo.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class httpPipelineInitialzer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new HttpPipelineInBoundHandler());
    }
}
