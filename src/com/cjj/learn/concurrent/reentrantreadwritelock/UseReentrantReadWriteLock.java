package com.cjj.learn.concurrent.reentrantreadwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UseReentrantReadWriteLock {
	/* 创建一个读写锁 */ 
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private ReadLock readLock = rwLock.readLock();
	private WriteLock writeLock = rwLock.writeLock();
	
	/* 共享数据，只能一个线程写数据，可以多个线程读数据 */  
    private Object data = null;  
	
    /** 
     * 读数据，可以多个线程同时读
     */  
	public void read(){
		try {
			readLock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "，准备读数据...");
			Thread.sleep(3000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "，读出的数据为:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}
	
	/** 
     * 写数据，多个线程不能同时 
     * @param data 
     */  
	public void write(Object data){
		try {
			/* 上写锁 */ 
			writeLock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "，准备写数据...");
			Thread.sleep(3000);
			this.data = data;  
			System.out.println("当前线程:" + Thread.currentThread().getName()  + "，写入的数据为:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 释放 写锁 */ 
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();
		
		/* 创建并启动读/写线程 */  
        for (int i = 0; i < 3; i++) {  
        	/*创建读线程*/  
            new Thread(new Runnable() {  
                @Override  
                public void run() {  
                	urrw.read();  
                }  
            }).start();  
              
            /*创建写线程*/  
            new Thread(new Runnable() {  
                @Override  
                public void run() {  
                    /*随机写入一个数*/  
                	urrw.write(new Random().nextInt(100));  
                }  
            }).start();  
        }  
	}
}
