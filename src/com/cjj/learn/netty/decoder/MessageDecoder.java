package com.cjj.learn.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MessageDecoder extends LengthFieldBasedFrameDecoder {
	
    // 头部信息的大小应该是 byte+byte+int = 1+1+8+4 = 14
    private static final int HEADER_SIZE = 14;

    public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null) {
            return null;
        }

        if (in.readableBytes() <= HEADER_SIZE) {
            return null;
        }

        in.markReaderIndex();

        byte magic = in.readByte();
        byte type = in.readByte();
        long requestId = in.readLong();
        int dataLength = in.readInt();

        // FIXME 如果 dataLength 过大，可能导致问题
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return null;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);

        String body = new String(data, "UTF-8");
        Message msg = new Message(magic, type, requestId, body);
        return msg;
    }
}
