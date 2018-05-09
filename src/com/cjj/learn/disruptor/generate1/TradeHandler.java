package com.cjj.learn.disruptor.generate1;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {  
	  
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        System.out.println("consumer:" + Thread.currentThread().getName() 
				+ " Event: price=" + event.getPrice() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
//        this.onEvent(event); 
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
        // 这里做具体的消费逻辑  
        event.setId(UUID.randomUUID().toString()); // 简单生成下ID  
        System.out.println("TradeHandler " + event.getId());  
    }  
}  