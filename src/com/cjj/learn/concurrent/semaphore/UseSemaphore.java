package com.cjj.learn.concurrent.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Semaphore;  
  
public class UseSemaphore {  
	
	static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
         
        @Override
        public void run() {
            try {
            	// 获取许可  阻塞等待
                semaphore.acquire();
                System.out.println("工人："+this.num+"，占用一个机器在生产...");
                // 模拟实际业务逻辑
                Thread.sleep(1000 * (new Random()).nextInt(5));  
                System.out.println("工人："+this.num+"，释放出机器");
                // 访问完后，释放  
                semaphore.release();           
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
    public static void main(String[] args) {  
        // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool(); 
        // 只能5个机器同时工作
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个工人访问  
        for (int index = 0; index < 20; index++) {  
        	Worker worker = new Worker(index,semp);
            exec.execute(worker);  
        } 
        
        System.out.println(semp.getQueueLength());
        
        // 退出线程池  
        exec.shutdown();  
    }  
}  