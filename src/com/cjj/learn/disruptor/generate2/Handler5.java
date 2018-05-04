package com.cjj.learn.disruptor.generate2;

import java.util.Random;
import java.util.UUID;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler5 implements EventHandler<Trade>,WorkHandler<Trade> {  
	  
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
    	event.setId(UUID.randomUUID().toString());   
    	event.setName("h5");
    	event.setPrice((new Random()).nextDouble());
    	System.out.println(event);
    	Thread.sleep(1000);
    }  
}  