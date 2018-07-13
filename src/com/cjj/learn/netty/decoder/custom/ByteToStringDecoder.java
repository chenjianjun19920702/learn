package com.cjj.learn.netty.decoder.custom;

import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ByteToStringDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		int len = 20; 
		if (in.readableBytes() >= len) {	
			byte[] by = new byte[len];
			for (int i=0;i<len;i++) {
				by[i] = in.readByte();
			}
			String msg = new String(by, "utf-8");
			System.out.println("ByteToStringDecoder decode msg is " + msg);
			// Read integer from inbound ByteBuf
			// add to the List of decodec messages
			out.add(msg);
		}
		
		/*int len = DataUtil.yisouResult.length(); 
		if (in.readableBytes() >= len) {	
			byte[] by = new byte[len];
			for (int i=0;i<len;i++) {
				by[i] = in.readByte();
			}
			String msg = new String(by, "utf-8");
			System.out.println("ByteToStringDecoder decode msg is " + msg);
			// Read integer from inbound ByteBuf
			// add to the List of decodec messages
			out.add(msg);
		}*/
	}
}
