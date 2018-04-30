package com.cjj.learn.concurrent.consumer.producer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BlockingQueue的写法最简单。
 * 核心思想是，把并发和容量控制封装在缓冲区中。
 * 而BlockingQueue的性质天生满足这个要求。
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
			Task task = blockingQueue.take();
			// 模拟消费
			Thread.sleep(1000* (new Random()).nextInt(5));
			System.out.println("thread 消费 name :" + Thread.currentThread().getName() + 
					",task id="+task.getId()+",name="+task.getName());
		}
		
	}
	
	public class ProducerImpl extends AbstractProducer implements Producer, Runnable {

		@Override
		public void produce() throws InterruptedException {
			// 模拟生产
			Thread.sleep(1000* (new Random()).nextInt(5));
			Task task = new Task(increTaskNo.getAndIncrement(), "cjj");
			blockingQueue.put(task);
			System.out.println("thread 生产 name:" + Thread.currentThread().getName() + 
					",task id="+task.getId()+",name="+task.getName());
		}

	}
	
	public static void main(String[] args) {
		BlockingQueueModel bqm = new BlockingQueueModel(3);
		for (int i = 0; i < 2; i++) {
	      new Thread(bqm.newRunnableConsumer()).start();
	    }
	    for (int i = 0; i < 5; i++) {
	      new Thread(bqm.newRunnableProducer()).start();
	    }
	}

}
