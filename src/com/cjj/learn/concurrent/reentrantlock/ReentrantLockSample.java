package com.cjj.learn.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample {
	
    public static void main(String[] args) {
        testSynchronized();
        testReentrantLock();
    }
    
    public static void testReentrantLock() {
        final SampleSupport1 support = new SampleSupport1();
        Thread first = new Thread(new Runnable() {
            public void run() {
                try {
                    support.doSomething();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"first");
        Thread second = new Thread(new Runnable() {
            public void run() {
                try {
                    support.doSomething();
                }
                catch (InterruptedException e) {
                    System.out.println("Second Thread Interrupted without executing counter++,beacuse it waits a long time.");
                }
            }
        },"second");
        executeTest(first, second);
    }
    
    public static void testSynchronized() {
        final SampleSupport2 support2 = new SampleSupport2();
        Runnable runnable = new Runnable() {
            public void run() {
                support2.doSomething();
            }
        };
        Thread third = new Thread(runnable,"third");
        Thread fourth = new Thread(runnable,"fourth");
        executeTest(third, fourth);
    }
    
    /**
     * Make thread a run faster than thread b,
     * then thread b will be interruted after about 1s.
     * @param a
     * @param b
     */
    public static void executeTest(Thread a, Thread b) {
        a.start();
        try {
            Thread.sleep(100);
            b.start(); 
            Thread.sleep(1000);	
            b.interrupt(); 	// 等待1s 还没有获取到锁 就直接自我中断
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

abstract class SampleSupport {
    protected int counter;
    /**
     * A simple countdown,it will stop after about 5s. 
     */
    public void startTheCountdown() {
        long currentTime = System.currentTimeMillis();
        for (;;) {
            long diff = System.currentTimeMillis() - currentTime;
            if (diff > 5000) {
                break;
            }
        }
    }
}

class SampleSupport1 extends SampleSupport {
    private final ReentrantLock lock = new ReentrantLock();
    public void doSomething() throws InterruptedException {
        lock.lockInterruptibly();
//        try {
//            lock.lockInterruptibly();
//        } catch (InterruptedException e) {
//            //做一些其它的事，不结束线程
//        }
        System.out.println(Thread.currentThread().getName() + " will execute counter++.");
        startTheCountdown();
        try {
            counter++;
        }
        finally {
            lock.unlock();
        }
    }
}

class SampleSupport2 extends SampleSupport {
    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + " will execute counter++.");
        startTheCountdown();
        counter++;
    }
}
