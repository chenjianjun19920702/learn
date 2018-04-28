package com.cjj.learn.concurrent.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个使用Condition实现生产者消费者的模型
 */
public class ProductQueue<T> {
	
	private final Lock lock = new ReentrantLock();
	
	private final T[] items; 
	
	 //  
    private int head, tail, count; 
	
	public ProductQueue(int maxSize) {
		super();
		this.items = (T[]) new Object[maxSize];
	}
	
	public ProductQueue() {  
        this(10);  
    }  

	private Condition notFull = lock.newCondition();
	
	private Condition notEmpty  = lock.newCondition();
	
	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			if (count == getCapacity()) {
				notFull.await();
			}
			items[tail] = t;  
			if (++tail == getCapacity()) {  
                tail = 0;  
            }  
            ++count;  
            notEmpty.signalAll();  
		} finally {
			lock.unlock();
		}
	}
	
	public T take() throws InterruptedException {  
        lock.lock();  
        try {  
            if (count == 0) {  
                notEmpty.await();  
            }  
            T ret = items[head];  
            items[head] = null;	//GC  
            //  
            if (++head == getCapacity()) {  
                head = 0;  
            }  
            --count;  
            notFull.signalAll();  
            return ret;  
        } finally {  
            lock.unlock();  
        }  
    }  
  
	
	/**
	 * 容量
	 */
	public int getCapacity() {
		return items.length;
	}
	
	/**
	 * 当前个数
	 */
	public int size() {
		lock.lock();
		try {  
            return count;  
        } finally {  
            lock.unlock();  
        }  
	}
	
	public static void main(String[] args) {
		final ProductQueue<String> pq = new ProductQueue<String>();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=1;i<20;i++) {
					try {
						System.out.println("线程：" + Thread.currentThread().getName() + "，存值：" + "t1_" + String.valueOf(i));
						pq.put("t1_" + String.valueOf(i));
//						Thread.sleep(1000 * (new Random()).nextInt(5));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=1;i<20;i++) {
					try {
						System.out.println("线程：" + Thread.currentThread().getName() + "，取值：" + pq.take());
//						Thread.sleep(1000 * (new Random()).nextInt(5));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
}
