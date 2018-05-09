package com.cjj.learn.disruptor;

import java.nio.ByteBuffer;
import com.lmax.disruptor.RingBuffer;

/**
 * 很明显的是：当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。
 * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。
 * 如果我们使用 RingBuffer.next() 获取一个事件槽，那么一定要发布对应的事件(finally 确保要发布)。
 * 如果不能发布事件，那么就会引起 Disruptor 状态的混乱。
 * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
 * 
 *  Disruptor的事件发布过程是一个两阶段提交的过程：
 *	第一步：先从 RingBuffer 获取下一个可以写入的事件的序号；
 *	第二步：获取对应的事件对象，将数据写入事件对象；
 *	第三部：将事件提交到 RingBuffer;
 *	事件只有在提交之后才会通知 EventProcessor 进行处理；
 */
public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;
	
	// 实例化事件队列
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	/**
	 * onData 用来发布事件，每调用一次就发布一次事件
	 * 它的参数会用过事件传递给消费者
	 */
	public void onData(ByteBuffer bb){
		// 1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
		long sequence = ringBuffer.next();
		try {
			// 2.用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
			LongEvent event = ringBuffer.get(sequence);
			// 3.获取要通过事件传递的业务数据
			event.setValue(bb.getLong(0));
		} finally {
			// 4.发布事件
			// 注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；
			// 如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
			ringBuffer.publish(sequence);
			System.out.println("生产的数据 sequence: " + sequence);
		}
	}
}
