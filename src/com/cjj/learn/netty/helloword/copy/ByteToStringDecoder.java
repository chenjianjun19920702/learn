package com.cjj.learn.netty.helloword.copy;

import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ByteToStringDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// Check if there are at least 4 bytes readable
		if (in.readableBytes() >= 4) {	
			int len = 4;  // 只读四个字节
			byte[] by = new byte[len];
			for (int i=0;i<len;i++) {
				by[i] = in.readByte();
			}
//	        in.readBytes(by);
			String msg = new String(by, "utf-8");
			System.out.println("ByteToStringDecoder decode msg is " + msg);
			// Read integer from inbound ByteBuf
			// add to the List of decodec messages
			out.add(msg);
		}
	}
}
