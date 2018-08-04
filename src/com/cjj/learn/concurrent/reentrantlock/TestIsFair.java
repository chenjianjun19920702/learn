package com.cjj.learn.concurrent.reentrantlock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
	公平锁（Fair）：加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得 
	非公平锁（Nonfair）：加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
	非公平锁性能比公平锁高5~10倍，因为公平锁需要在多核的情况下维护一个队列
	首先Java中的ReentrantLock 默认的lock()方法采用的是非公平锁。
 *
 */
public class TestIsFair {
	
	public TestIsFair(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod() {
		try {
			lock.lock();
			System.out.println("ThreadName=" + Thread.currentThread().getName() + ",获得锁");
			Thread.sleep(1000 * (new Random()).nextInt(5));  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private ReentrantLock lock;
	
	public static void main(String[] args) {
		boolean isFair = false;	// 默认
//		isFair = true;
		final TestIsFair fair = new TestIsFair(isFair);
		Thread t1 = new Thread() {
			public void run() {
				System.out.println("我进来了："+Thread.currentThread().getName());
				fair.serviceMethod();
			}
		};
		t1.start();
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i=0; i<5; i++) {
			service.execute(t1);
		}
		
		service.shutdown();
	}
}
