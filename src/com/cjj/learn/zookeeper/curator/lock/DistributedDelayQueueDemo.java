package com.cjj.learn.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedDelayQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.Date;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/13 11:30
 */
public class DistributedDelayQueueDemo {

	private static final String PATH = "/example/queue";

	public static void main(String[] args) throws Exception {
//		TestingServer server = new TestingServer();
		CuratorFramework client = null;
		DistributedDelayQueue<String> queue = null;
		try {
			client = CuratorFrameworkFactory.newClient("10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181", new ExponentialBackoffRetry(1000, 3));
			client.getCuratorListenable().addListener((client1, event) -> System.out.println("CuratorEvent: " + event.getType().name()));

			client.start();
			QueueConsumer<String> consumer = createQueueConsumer();
			QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), PATH);
			queue = builder.buildDelayQueue();
			queue.start();

			for (int i = 0; i < 10; i++) {
				/**
				 *  注意delayUntilEpoch不是离现在的一个时间间隔，
				 *  比如20毫秒，而是未来的一个时间戳，如 System.currentTimeMillis() + 10秒。 
				 *  如果delayUntilEpoch的时间已经过去，消息会立刻被消费者接收。
				 */
				queue.put("test-" + i, System.currentTimeMillis() + 10000);
			}
			System.out.println(new Date().getTime() + ": already put all items");


			Thread.sleep(2000);

		} catch (Exception ex) {

		} finally {
			CloseableUtils.closeQuietly(queue);
			CloseableUtils.closeQuietly(client);
//			CloseableUtils.closeQuietly(server);
		}
	}

	private static QueueSerializer<String> createQueueSerializer() {
		return new QueueSerializer<String>() {

			@Override
			public byte[] serialize(String item) {
				return item.getBytes();
			}

			@Override
			public String deserialize(byte[] bytes) {
				return new String(bytes);
			}

		};
	}

	private static QueueConsumer<String> createQueueConsumer() {

		return new QueueConsumer<String>() {

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				System.out.println("connection new state: " + newState.name());
			}

			@Override
			public void consumeMessage(String message) throws Exception {
				System.out.println(new Date().getTime() + ": consume one message: " + message);
			}

		};
	}
}
