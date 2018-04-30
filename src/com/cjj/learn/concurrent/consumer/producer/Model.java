package com.cjj.learn.concurrent.consumer.producer;

public interface Model {
	
	Runnable newRunnableConsumer();
	
	Runnable newRunnableProducer();
}
