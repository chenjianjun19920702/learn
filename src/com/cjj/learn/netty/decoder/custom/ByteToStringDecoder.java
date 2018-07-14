package com.cjj.learn.netty.decoder.custom;

import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ByteToStringDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		System.out.println("************decode is begin*************");
		
		int endReader = 0;
		int beginReader = in.readerIndex();
		int readableBytes = in.readableBytes();
		int totalIndex = beginReader + readableBytes;
		int len = DataUtil.yisouResult.length(); 
		System.out.println("beginReader is " + beginReader);
		System.out.println("readableBytes is " + readableBytes);
		if (readableBytes >= len) {
			String findBegin = "";
			String findEnd = "";
			boolean hasFindBegin = false;
			for (int i=beginReader;i<totalIndex;i++) {
				byte b = in.getByte(i);
				if (hasFindBegin) {	// find end dataLen
					findEnd += (char) b;
					if (!DataUtil.endFlagS.startsWith(findEnd)) {
						findEnd = "";
					}
					if (findEnd.equals(DataUtil.endFlagS)) {
						int restLen = DataUtil.endFlagM.length()+DataUtil.endFlagE.length();
						if (i+restLen < totalIndex) {
							byte s = in.getByte(i+restLen);
							String endFlagE = "" + (char) s;
							if (endFlagE.equals(DataUtil.endFlagE)) {
								endReader = i+restLen+1;
								break;
							} else {
								findEnd = "";
							}
						} else {
							findEnd = "";
						}
					}
				} else { // find begin position
					findBegin += (char) b;
					if (!DataUtil.beginFlag.startsWith(findBegin)) {
						findBegin = "";
					}
					if (findBegin.equals(DataUtil.beginFlag)) {
						hasFindBegin = true;
						if (i > beginReader+DataUtil.beginFlag.length()) {	// 说明有脏数据
							in.skipBytes(i-beginReader-DataUtil.beginFlag.length()+1);
							return;
						}
					}
				}
			}
			// 找到了有效完整数据包
			if (endReader > 0) {
				int dataLen = endReader - beginReader;
				byte[] data = new byte[dataLen];
				for (int i=0;i<dataLen;i++) {
					data[i] = in.readByte();
				}
				String msg = new String(data, "utf-8");
				System.out.println("ByteToStringDecoder decode msg is " + msg);
				// Read integer from inbound ByteBuf
				// add to the List of decodec messages
				out.add(msg);
			}
			System.out.println("readableBytes is " + in.readableBytes());
			System.out.println("readerIndex is " + in.readerIndex());
		}
		
		System.out.println("************decode is end*************");
		
//		int len = DataUtil.yisouResult.length(); 
//		if (in.readableBytes() >= len) {	
//			byte[] by = new byte[len];
//			for (int i=0;i<len;i++) {
//				by[i] = in.readByte();
//			}
//			String msg = new String(by, "utf-8");
//			System.out.println("ByteToStringDecoder decode msg is " + msg);
//			// Read integer from inbound ByteBuf
//			// add to the List of decodec messages
//			out.add(msg);
//		}
	}
}
