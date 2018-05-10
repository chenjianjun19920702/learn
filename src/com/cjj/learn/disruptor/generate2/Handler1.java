package com.cjj.learn.disruptor.generate2;

import java.util.Random;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler1 implements EventHandler<Trade>,WorkHandler<Trade> {  
	  
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
    	/*System.out.println("handler1: set name");
    	event.setName("h1");*/
    	System.out.println("handler1: " + event);
    	Thread.sleep(new Random().nextInt(3)*1000);
    }  
}  