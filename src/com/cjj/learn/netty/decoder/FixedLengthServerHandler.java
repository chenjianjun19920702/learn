package com.cjj.learn.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class FixedLengthServerHandler  extends ChannelHandlerAdapter {
	
	private int counter = 0;
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
        System.out.println("接收客户端msg: " +  msg);

        ByteBuf echo = Unpooled.copiedBuffer(String.format("Hello from server:", counter).getBytes());
        ctx.writeAndFlush(echo);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
