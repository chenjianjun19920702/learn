package com.cjj.learn.java.jedis.pushandpop;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedisQuene {

	public RedisQuene() {
		// TODO Auto-generated constructor stub
	}

	public static byte[] redisKey = "key".getBytes(); 
	
	static{  
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}  
	
	// 反向取出
	private static void pop() throws Exception {  
		byte[] bytes = JedisUtil.rpop(redisKey);  
		Message msg = (Message) ObjectUtil.bytesToObject(bytes);  
		if(msg != null){  
			System.out.println(msg.toString());  
		}  
	}  

	private static void init() throws Exception {  
//		for (int i = 0; i < 1000; i++) {
//            Message message = new Message(i, "这是第" + i + "个内容");
//            JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(message));
//        }
	}  
	
	public static void main(String[] args) throws Exception {  
		
		// 先进先出
		for (int i = 0; i < 1000; i++) {
//			pop();
		}
		
		// 顺序取出
		List<byte[]> res = JedisUtil.lpopList(redisKey);
		for (byte[] obj : res) {
			Message msg = (Message) ObjectUtil.bytesToObject(obj);
			System.out.println(msg.toString());
		}
		
		/*
		//在线程可以通过await（）之前必须调用countDown（）的次数
        //具有计数1的 CountdownLatch 可以用作”启动大门”，来立即启动一组线程；
        final CountDownLatch begin = new CountDownLatch(1);  //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //如果当前计数为零，则此方法立即返回。
                        //如果当前计数大于零，则当前线程将被禁用以进行线程调度，并处于休眠状态，直至发生两件事情之一：
                        //或调用countDown（）方法，计数达到零;
                        //或一些其他线程中断当前线程。
 
                        //等待直到 CountDownLatch减到1
                        begin.await();
                        try {
							pop();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.submit(runnable);
        }
        System.out.println("开始执行");
        //减少锁存器的计数，如果计数达到零，释放所有等待的线程。
        // begin减一，开始并发执行
        begin.countDown();
        //此方法不等待先前提交的任务完成执行
        //exec.shutdown();
        //为了保证先前提交的任务完成执行 使用此方法
        exec.awaitTermination(10, TimeUnit.MILLISECONDS);
        */
	}  
}
