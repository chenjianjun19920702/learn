package com.cjj.learn.netty.decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class FixedLengthServer {

	public static void main(String[] args) throws Exception {
		// 第一个线程组 是用于接收Client端连接的
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 第二个线程组 是用于实际的业务处理操作的
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		// 创建一个辅助类Bootstrap，就是对我们的Server进行一系列的配置
		ServerBootstrap b = new ServerBootstrap(); 
		// 把俩个工作线程组加入进来
		b.group(bossGroup, workerGroup)
		// 我要指定使用NioServerSocketChannel这种类型的通道
		 .channel(NioServerSocketChannel.class)
		// 一定要使用 childHandler 去绑定具体的 事件处理器
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
				p.addLast(new FixedLengthFrameDecoder(1<<5));
                p.addLast(new StringDecoder());
                p.addLast(new StringEncoder());
                p.addLast(new FixedLengthServerHandler());
			}
		});

		// 绑定指定的端口 进行监听
		ChannelFuture f = b.bind(8765).sync(); 
		
//		Thread.sleep(1000000);
		// Wait until the server socket is closed.
		f.channel().closeFuture().sync();
		
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
