package com.cjj.learn.netty.decoder.custom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class StringToByteEncoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		System.out.println("StringToByteEncoder encode msg is " + msg);
		out.writeBytes(msg.getBytes());
	}
}
