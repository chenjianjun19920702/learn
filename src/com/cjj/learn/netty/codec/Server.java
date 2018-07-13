package com.cjj.learn.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	public void bind(int port) throws Exception {
		// 服务器线程组 用于网络事件的处理 一个用于服务器接收客户端的连接
		// 另一个线程组用于处理 SocketChannel 的网络读写
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// NIO 服务器端的辅助启动类 降低服务器开发难度
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)		// 类似 NIO 中serverSocketChannel
					.option(ChannelOption.SO_BACKLOG, 1024)		// 配置 TCP 参数
					.option(ChannelOption.SO_BACKLOG, 1024) 	// 设置 tcp 缓冲区
					.option(ChannelOption.SO_SNDBUF, 32 * 1024) // 设置发送缓冲大小
					.option(ChannelOption.SO_RCVBUF, 32 * 1024) // 这是接收缓冲大小
					.option(ChannelOption.SO_KEEPALIVE, true) 	// 保持连接
					.childHandler(new ChildChannelHandler());	// 最后绑定 I/O 事件的处理类

			// 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的 ChildChannelHandler
			ChannelFuture f = serverBootstrap.bind(port).sync();
			System.out.println("Server启动");
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			System.out.println("服务器优雅的释放了线程资源...");
		}
	}

	/**
	 * 网络事件处理器
	 */
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// 自定义编码器
			ch.pipeline().addLast(new IntegerToByteEncoder());
			// 自定义解码器
			ch.pipeline().addLast(new ByteToIntegerDecoder());
			// 服务端的处理器
			ch.pipeline().addLast(new ServerHandler());
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 9998;
		new Server().bind(port);
	}
}
