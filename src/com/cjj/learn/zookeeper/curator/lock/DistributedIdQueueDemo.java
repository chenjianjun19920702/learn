package com.cjj.learn.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedIdQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/12 1:50
 */
public class DistributedIdQueueDemo {

	private static final String PATH = "/example/queue";

	public static void main(String[] args) throws Exception {
//		TestingServer server = new TestingServer();
		CuratorFramework client = null;
		DistributedIdQueue<String> queue = null;
		final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
		try {
			client = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new ExponentialBackoffRetry(1000, 3));
			client.getCuratorListenable().addListener((client1, event) -> System.out.println("CuratorEvent: " + event.getType().name()));

			client.start();
			QueueConsumer<String> consumer = createQueueConsumer();
			QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), PATH);
			queue = builder.buildIdQueue();
			queue.start();

			for (int i = 0; i < 10; i++) {
				queue.put(" test-" + i, "Id" + i);
				Thread.sleep((long) (15 * Math.random()));
				queue.remove("Id" + i);
			}

			Thread.sleep(20000);

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
				System.out.println("consume one message: " + message);
			}

		};
	}
}
