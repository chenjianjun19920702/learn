package com.cjj.learn.disruptor.generate2;

import java.util.Random;
import java.util.UUID;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;

public class Handler3 implements EventHandler<Trade> {
    @Override  
    public void onEvent(Trade event, long sequence,  boolean endOfBatch) throws Exception {  
//    	event.setId(UUID.randomUUID().toString());   
    	event.setName("h3");
    	event.setPrice((new Random()).nextDouble());
    	System.out.println(event);
    	Thread.sleep(1000);
    }  
}
