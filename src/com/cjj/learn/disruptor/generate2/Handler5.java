package com.cjj.learn.disruptor.generate2;

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
    	/*System.out.println("handler5: set name");
    	event.setName("h5");*/
    	System.out.println("handler5: " + event);
    }  
}  