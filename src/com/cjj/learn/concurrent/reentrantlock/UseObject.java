package com.cjj.learn.concurrent.reentrantlock;

import java.util.concurrent.TimeUnit;

/**
 * 条件变量很大一个程度上是为了解决Object.wait/notify/notifyAll难以使用的问题
 */
public class UseObject {

	private Object obj = new Object();
	
	public void method1() throws InterruptedException{
		synchronized (obj) {
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进去，开始执行...");
			obj.wait(5000);
			System.out.println("当前线程：" + Thread.currentThread().getName() +"被唤醒，继续执行...");
		}
	}
	
	public void method2(){
		synchronized (obj) {
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进去，开始执行...");
			obj.notify();
//			try {
//				TimeUnit.SECONDS.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println("当前线程：" + Thread.currentThread().getName() +"被唤醒，继续执行...");
		}
		// 同步代码块执行完  才会释放锁  通知t1
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final UseObject uc = new UseObject();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					uc.method1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
