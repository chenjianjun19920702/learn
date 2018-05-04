package com.cjj.learn.disruptor;

/**
 * 生产者传递一个 long 类型的值给消费者
 * 声明一个 Event 来包含需要传递的数据
 * http://ifeve.com/disruptor-getting-started/
 */
public class LongEvent { 
	
    private long value;
    
    public long getValue() { 
        return value; 
    } 
 
    public void setValue(long value) { 
        this.value = value; 
    } 
} 