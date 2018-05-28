package com.cjj.learn.netty.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageLengthFeildServerHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
		System.out.println("server read msg:" + msg);

        Message resp = new Message(msg.getMagicType(), msg.getType(), msg.getRequestId(), "Hello world from server");
        ctx.writeAndFlush(resp);
	}
	
}
