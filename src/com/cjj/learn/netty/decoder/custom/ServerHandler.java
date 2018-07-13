package com.cjj.learn.netty.decoder.custom;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// do something msg
		String request = (String) msg;
		System.out.println("Server 接收消息: " + request);
		// 写回给客户端
		ctx.writeAndFlush(Unpooled.copiedBuffer(request.getBytes()));
		//.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
