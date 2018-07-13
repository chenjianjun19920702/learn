package com.cjj.learn.netty.helloword.copy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static void main(String[] args) throws Exception {

		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workgroup)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline p = sc.pipeline();
                p.addLast(new ByteToStringDecoder());
                p.addLast(new StringToByteEncoder());
                p.addLast(new ClientHandler());
			}
		});

		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		
		// buf
		// cf1.channel().writeAndFlush(Unpooled.copiedBuffer("你好，我是客户端".getBytes()));
		
		// 会发生粘包、拆包的问题
		final CountDownLatch begin = new CountDownLatch(1); // 为0时开始执行
		final ExecutorService exec = Executors.newFixedThreadPool(100);
		
		for (int i = 0; i < 10; i++) {
			final int index = i+1;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						begin.await(); // 等待直到 CountDownLatch减到1
						cf1.channel().writeAndFlush(Unpooled.copiedBuffer(("hello,this is " + index + ".").getBytes()));
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

		cf1.channel().closeFuture().sync();
		workgroup.shutdownGracefully();
	}
}
