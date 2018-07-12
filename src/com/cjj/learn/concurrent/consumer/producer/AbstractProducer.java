package com.cjj.learn.concurrent.consumer.producer;

public abstract class AbstractProducer implements Producer, Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				produce();
			} catch(InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
