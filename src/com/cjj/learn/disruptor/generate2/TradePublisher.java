package com.cjj.learn.disruptor.generate2;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import com.cjj.learn.disruptor.generate1.Trade;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

public class TradePublisher implements Runnable {  
	
    Disruptor<Trade> disruptor;  
    private CountDownLatch latch;  
    
    private static int LOOP = 10;	//模拟百万次交易的发生  
  
    public TradePublisher(CountDownLatch latch,Disruptor<Trade> disruptor) {  
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
    	TradeEventTranslator tradeTransloator = new TradeEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(tradeTransloator);  
        }  
        latch.countDown();  
    }  
      
}  
  
class TradeEventTranslator implements EventTranslator<Trade>{  
    
	@Override  
    public void translateTo(Trade event, long sequence) {  
        this.generateTrade(event);  
    }  
    
	private Trade generateTrade(Trade trade){  
		UUID uu = UUID.randomUUID();
		trade.setId(uu.toString());   
		System.out.println("producer " + uu.toString());
        return trade;  
    }  
	
}  