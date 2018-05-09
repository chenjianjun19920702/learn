package com.cjj.learn.disruptor;

import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventHandler;

/**
 * 事件消费者，也就是一个事件处理器
 * 
 * 定义如何处理消息的地方，此处执行速度要足够快。
 * 否则，会影响RingBuffer后续没空间加入新的数据。
 * 因此，不能做业务耗时操作。建议另外开始 java 线程池处理消息。
 * 
 * 这个事件处理器简单地把事件中存储的数据打印到终端
 */
public class LongEventHandler implements EventHandler<LongEvent>  {

	@Override
	public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
		// 模拟消费的耗时
		TimeUnit.SECONDS.sleep(1);
		System.out.println("consumer:" + Thread.currentThread().getName() 
				+ " Event: value=" + longEvent.getValue() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}
