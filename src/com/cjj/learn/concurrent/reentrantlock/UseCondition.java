package com.cjj.learn.concurrent.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量很大一个程度上是为了解决Object.wait/notify/notifyAll难以使用的问题
 */
public class UseCondition {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁，进入等待状态..");
//			Thread.sleep(3000);
//			condition.await();	// Object wait 当前线程所以挂起状态就是要释放竞争资源的锁
			System.out.println(condition.await(10, TimeUnit.SECONDS));
			System.out.println("当前线程：" + Thread.currentThread().getName() +"被唤醒，继续执行...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁，进入..");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
			condition.signal();		//Object notify
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁...");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method1();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method2();
			}
		}, "t2");
		
		t1.start();
		TimeUnit.SECONDS.sleep(2);
		t2.start();
	}
}
