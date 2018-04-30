package com.cjj.learn.concurrent.consumer.producer;

public abstract class AbstractConsumer implements Consumer, Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				consume();
			} catch(InterruptedException e) {
				e.printStackTrace();
				break;
			}
			
		}
	}

}
