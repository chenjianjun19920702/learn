package com.cjj.learn.concurrent.consumer.producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BlockingQueue 的写法最简单。
 * 核心思想是，把并发和容量控制封装在缓冲区中。
 * 而 BlockingQueue 的性质天生满足这个要求。
 */
public class BlockingQueueModel implements Model {
	
	private final BlockingQueue<Task> blockingQueue;
	
	private final AtomicInteger increTaskNo = new AtomicInteger(0);
	
	public BlockingQueueModel() {
		blockingQueue = new LinkedBlockingQueue<Task>(10);
	}
	
	public BlockingQueueModel(int size) {
		blockingQueue = new LinkedBlockingQueue<Task>(size);
	}

	@Override
	public Runnable newRunnableConsumer() {
		return new ConsumerImpl();
	}

	@Override
	public Runnable newRunnableProducer() {
		return new ProducerImpl();
	}
	
	public class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

		@Override
		public void consume() throws InterruptedException {
			System.out.println("thread 消费 name :" + Thread.currentThread().getName() + 
					",等待消费......");
			Task task = blockingQueue.take();
			// 模拟消费时间
			Thread.sleep(1000* (new Random()).nextInt(5));
			System.out.println("thread 消费 name :" + Thread.currentThread().getName() + 
					",task id="+task.getId()+",name="+task.getName());
		}
		
	}
	
	public class ProducerImpl extends AbstractProducer implements Producer, Runnable {

		@Override
		public void produce() throws InterruptedException {
			System.out.println("thread 生产 name:" + Thread.currentThread().getName() + 
					",准备生产......");
			// 模拟生产时间
			Thread.sleep(1000* (new Random()).nextInt(5));
			Task task = new Task(increTaskNo.getAndIncrement(), "cjj");
			blockingQueue.put(task);
			System.out.println("thread 生产 name:" + Thread.currentThread().getName() + 
					",task id="+task.getId()+",name="+task.getName());
		}

	}
	
	public static void main(String[] args) {
		
		BlockingQueueModel bqm = new BlockingQueueModel(3);
		// 多生产者
		for (int i = 0; i < 2; i++) {
	      new Thread(bqm.newRunnableConsumer()).start();
	    }
		// 多消费者
	    for (int i = 0; i < 1; i++) {
	      new Thread(bqm.newRunnableProducer()).start();
	    }
	}

}
