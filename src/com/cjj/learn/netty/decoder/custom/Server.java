package com.cjj.learn.netty.decoder.custom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

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
//		.option(ChannelOption.SO_BACKLOG, 32 * 1024) 	// 设置tcp缓冲区
//		.option(ChannelOption.SO_SNDBUF, 32 * 1024) 	// 设置发送缓冲大小
//		.option(ChannelOption.SO_RCVBUF, 32 * 1024) 	// 这是接收缓冲大小
//		.option(ChannelOption.SO_KEEPALIVE, true) 		// 保持连接
		// 一定要使用 childHandler 去绑定具体的 事件处理器
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
                p.addLast(new ByteToStringDecoder());
                p.addLast(new StringToByteEncoder());
                p.addLast(new ServerHandler());
			}
		});

		// 绑定指定的端口 进行监听
		ChannelFuture f = b.bind(8765).sync(); 
		System.out.println("Server 启动。。。");
		
		// Thread.sleep(1000000);
		f.channel().closeFuture().sync();

		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
