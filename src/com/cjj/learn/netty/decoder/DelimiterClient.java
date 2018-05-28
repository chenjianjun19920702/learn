package com.cjj.learn.netty.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class DelimiterClient {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workgroup)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
                p.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$".getBytes())));
                p.addLast(new StringDecoder());
                p.addLast(new StringEncoder());
                p.addLast(new DelimiterClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		
		// buf
		cf1.channel().writeAndFlush("777" + "$");
		
		cf1.channel().closeFuture().sync();
		workgroup.shutdownGracefully();
	}
}
