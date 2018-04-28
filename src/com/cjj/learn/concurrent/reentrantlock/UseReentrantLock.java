package com.cjj.learn.concurrent.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock {
	
	private Lock lock = new ReentrantLock();
	
	public void method1(){
		System.out.println("当前线程:" + Thread.currentThread().getName() + "等待进入method1..");
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method1..");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method1..");
		}
	}
	
	public void method2(){
		System.out.println("当前线程:" + Thread.currentThread().getName() + "等待进入method2..");
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");
		}
	}
	
	public static void main(String[] args) {

		final UseReentrantLock ur = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.method1();
				ur.method2();
			}
		}, "t1");

		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.method1();
				ur.method2();
			}
		}, "t2");

		t2.start();
		//System.out.println(ur.lock.getQueueLength());
	}
	
	
}
