package com.cjj.learn.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 需要让 disruptor 为我们创建事件，
 * 我们同时还声明了一个 EventFactory 来实例化 Event 对象。
 */
public class LongEventFactory implements EventFactory<LongEvent> { 

    @Override 
    public LongEvent newInstance() { 
        return new LongEvent(); 
    } 
} 