package com.cjj.learn.netty.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MessageLengthFeildClient {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workgroup)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
                p.addLast(new MessageDecoder(1<<20, 10, 4));
                p.addLast(new MessageEncoder());
                p.addLast(new MessageLengthFeildClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		
		// message
		Message msg = new Message();
		msg.setMagicType((byte) 0xAF);
		msg.setType((byte) 0xAF);
		msg.setRequestId(123);
		msg.setBody("撒大声地所");
		cf1.channel().writeAndFlush(msg);
		
		cf1.channel().closeFuture().sync();
		workgroup.shutdownGracefully();
	}
}
