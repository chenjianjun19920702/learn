package com.cjj.learn.netty.codec;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	/**
	 * 连接服务器
	 */
	public void connect(int port, String host) throws Exception {
		// 配置客户端 NIO 线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			// 客户端辅助启动类 对客户端配置
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ClientChannelHandler());
			// 异步链接服务器 同步等待链接成功
			final ChannelFuture f = b.connect(host, port).sync();
			/*// 发送消息
			Thread.sleep(1000);
			f.channel().writeAndFlush(777);
			f.channel().writeAndFlush(666);
			Thread.sleep(2000);
			f.channel().writeAndFlush(888);*/
			
			// 
			final CountDownLatch begin = new CountDownLatch(1); // 为0时开始执行
			final ExecutorService exec = Executors.newFixedThreadPool(100);
			
			for (int i = 0; i < 20; i++) {
				final int index = i+1;
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							begin.await(); // 等待直到 CountDownLatch减到1
							f.channel().writeAndFlush(index);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				exec.submit(runnable); 
			}
			System.out.println("开始执行");    
		    begin.countDown(); // begin减一，开始并发执行  
		    exec.shutdown();
			
			// 等待链接关闭
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
			System.out.println("客户端优雅的释放了线程资源...");
		}
	}

	/**
	 * 网络事件处理器
	 */
	private class ClientChannelHandler extends
			ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// 自定义编码器
			ch.pipeline().addLast(new IntegerToByteEncoder());
			// 自定义解码器
			ch.pipeline().addLast(new ByteToIntegerDecoder());
			// 客户端处理器
			ch.pipeline().addLast(new ClientHandler());
		}
	}

	public static void main(String[] args) throws Exception {
		new Client().connect(9998, "127.0.0.1");
	}
}
