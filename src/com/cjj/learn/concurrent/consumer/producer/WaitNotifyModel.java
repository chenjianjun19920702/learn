package com.cjj.learn.concurrent.consumer.producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 如果不能将并发与容量控制都封装在缓冲区中，就只能由消费者与生产者完成。
 最简单的方案是使用朴素的wait && notify机制。
 */
public class WaitNotifyModel implements Model {
	
	private final Object block = new Object();
	
	private final Queue<Task> buffer = new LinkedList<Task>();
	
	private final AtomicInteger increTaskNo = new AtomicInteger(0);
	
	private final int cap;
	
	public WaitNotifyModel(int cap) {
		this.cap = cap;
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
			synchronized(block) {
				while (buffer.size() == 0) {
					block.wait();
				}
				Task task = buffer.poll();
		        assert task != null;
		        // 固定时间范围的消费，模拟相对稳定的服务器处理过程
		        Thread.sleep(500 + (long) (Math.random() * 500));
		        System.out.println("consume: " + task.getId());
		        block.notifyAll();
			}
			
		}
		
	}
	
	public class ProducerImpl extends AbstractProducer implements Producer, Runnable {

		@Override
		public void produce() throws InterruptedException {
			synchronized(block) {
				while (buffer.size() == cap) {
					block.wait();
				}
				Task task = new Task(increTaskNo.getAndIncrement(), "cjj");
		        assert task != null;
		        // 固定时间范围的消费，模拟相对稳定的服务器处理过程
		        Thread.sleep(500 + (long) (Math.random() * 500));
		        buffer.offer(task);
		        System.out.println("produce: " + task.getId());
		        block.notifyAll();
			}
		}

	}
	
	public static void main(String[] args) {
		WaitNotifyModel bqm = new WaitNotifyModel(3);
		for (int i = 0; i < 20; i++) {
	      new Thread(bqm.newRunnableConsumer()).start();
	    }
	    for (int i = 0; i < 5; i++) {
	      new Thread(bqm.newRunnableProducer()).start();
	    }
	}

}
