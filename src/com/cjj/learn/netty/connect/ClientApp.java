package com.cjj.learn.netty.connect;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class ClientApp {
	public static final String HOST = "127.0.0.1";
	public static int PORT = 2222;
	public static void main(String[] args) throws Exception {
		
		ClientConnection cc = new ClientConnection();
		cc.connect(HOST, PORT);
		Channel channel = null;
		for (int i=0;i<1;i++) {
			channel = cc.getChannel();
			// buf
			TimeUnit.SECONDS.sleep(5);
			System.out.println("channel is " + channel);
			System.out.println(channel.isActive());
			System.out.println(channel.isOpen());
			System.out.println(channel.isWritable());
			channel.writeAndFlush(Unpooled.copiedBuffer(("hiiiiiiii" + i).getBytes()));
		}

		// 会发生粘包、拆包的问题
		/*final CountDownLatch begin = new CountDownLatch(1); // 为0时开始执行
		final ExecutorService exec = Executors.newFixedThreadPool(100);

		for (int i = 0; i < 100; i++) {
			final int index = i+1;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						begin.await(); // 等待直到 CountDownLatch减到1
						channel.writeAndFlush(Unpooled.copiedBuffer(("hiiiiii" + index + ".").getBytes()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			exec.submit(runnable); 
		}
		System.out.println("开始执行");    
	    begin.countDown(); // begin减一，开始并发执行  
	    exec.shutdown();*/

		channel.closeFuture().sync();
	}
}
