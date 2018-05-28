package com.cjj.learn.netty.decoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class FixedLengthClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send the message to Server
		for (int i=0; i<100; i++) {
			String msg = "hello from client " + i;
			System.out.println("client send message: " + msg);
            ctx.writeAndFlush(msg);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收服务端msg: " + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
