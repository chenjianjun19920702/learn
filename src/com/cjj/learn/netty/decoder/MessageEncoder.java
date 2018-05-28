package com.cjj.learn.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.charset.Charset;

public class MessageEncoder extends MessageToByteEncoder<Message> {
	
    private final Charset charset = Charset.forName("utf-8");

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //
        out.writeByte(msg.getMagicType());
        out.writeByte(msg.getType());
        out.writeLong(msg.getRequestId());

        byte[] data = msg.getBody().getBytes(charset);
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
