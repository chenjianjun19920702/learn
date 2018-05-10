package com.cjj.learn.disruptor.generate2;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;

public class Handler3 implements EventHandler<Trade> {
    @Override  
    public void onEvent(Trade event, long sequence,  boolean endOfBatch) throws Exception {  
    	/*System.out.println("handler3: set name");
    	event.setName("h3");*/
    	System.out.println("handler3: " + event);
    }  
}
