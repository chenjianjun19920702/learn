package com.cjj.learn.netty.decoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LineClientHandler extends ChannelHandlerAdapter {

	private int count = 0;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send the message to Server
		for (int i=0; i<100; i++) {
			String msg = "hello from client " + i;
			System.out.println("client send message: " + msg);
			ctx.writeAndFlush(msg + System.getProperty("line.separator"));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
        count++;
        System.out.println("client read msg: " +  body + ", count: " + count);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
