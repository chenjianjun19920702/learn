package com.cjj.learn.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntegerToByteEncoder extends MessageToByteEncoder<Integer> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out)
			throws Exception {
		System.out.println("IntegerToByteEncoder encode msg is " + msg);
		out.writeInt(msg);
	}
}
