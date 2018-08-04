package com.cjj.learn.concurrent.cyclicbarrier;
import java.io.IOException;  
import java.util.Random;  
import java.util.concurrent.BrokenBarrierException;  
import java.util.concurrent.CyclicBarrier;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException; 
public class UseCyclicBarrierTimeout {

	static class Runner implements Runnable {  
	    private CyclicBarrier barrier;  
	    private String name;  
	    private long timeout;
	    
	    public Runner(CyclicBarrier barrier, String name, long timeout) {  
	        this.barrier = barrier;  
	        this.name = name;  
	        this.timeout = timeout;
	    }  
	    
	    @Override  
	    public void run() {  
	    	System.out.println("线程:"+Thread.currentThread().getName()+",run...");
	        try {  
	        	// 以睡眠来模拟操作
	            Thread.sleep(1000 * (new Random()).nextInt(5));  
	            System.out.println("线程:"+Thread.currentThread().getName()+",处理完毕，等待其他线程操作");
	            try {
					barrier.await(timeout, TimeUnit.MILLISECONDS);
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        } catch (BrokenBarrierException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("线程:"+Thread.currentThread().getName()+",Go!!");  
	    }  
	} 
	
    public static void main(String[] args) throws IOException, InterruptedException {  
        CyclicBarrier barrier = new CyclicBarrier(3);  // 3 
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        
        executor.submit(new Thread(new Runner(barrier, "zhangsan", 1000)));  
        executor.submit(new Thread(new Runner(barrier, "lisi", 2000)));  
        executor.submit(new Thread(new Runner(barrier, "wangwu", 3000)));  
  
        executor.shutdown();  
    }  
}  