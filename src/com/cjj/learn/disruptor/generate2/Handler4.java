package com.cjj.learn.disruptor.generate2;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler4 implements EventHandler<Trade>,WorkHandler<Trade> {  
	  
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
    	/*System.out.println("handler4: set name");
    	event.setName("h4");*/
    	System.out.println("handler4: " + event);
    }  
}  