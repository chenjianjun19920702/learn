package com.cjj.learn.netty.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class LineBasedClient {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workgroup)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
                p.addLast(new LineBasedFrameDecoder(1024));
                p.addLast(new StringDecoder());
                p.addLast(new StringEncoder());
                p.addLast(new LineClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		
		// buf
		cf1.channel().writeAndFlush("777" + System.getProperty("line.separator"));
		
		cf1.channel().closeFuture().sync();
		workgroup.shutdownGracefully();
	}
}
