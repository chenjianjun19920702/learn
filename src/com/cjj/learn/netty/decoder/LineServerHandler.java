package com.cjj.learn.netty.decoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LineServerHandler  extends ChannelHandlerAdapter {
	
	private int count = 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		count++;
        String body = (String) msg;
        System.out.println("server read msg: " + body + ", count: " + count);

        String response = "hello from server " + System.getProperty("line.separator");
        ctx.writeAndFlush(response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
