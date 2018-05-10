package com.cjj.learn.disruptor.generate2;

import java.util.Random;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;

public class Handler2 implements EventHandler<Trade> {  
	  
    @Override  
    public void onEvent(Trade event, long sequence,  boolean endOfBatch) throws Exception {  
    	/*System.out.println("handler2: set name");
    	event.setName("h2");*/
    	System.out.println("handler2: " + event);
    	Thread.sleep(new Random().nextInt(2)*1000);
    }  
      
}  