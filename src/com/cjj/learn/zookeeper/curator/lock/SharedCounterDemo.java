package com.cjj.learn.zookeeper.curator.lock;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SharedCount代表计数器， 可以为它增加一个SharedCountListener，
 * 当计数器改变时此Listener可以监听到改变的事件，
 * 而SharedCountReader可以读取到最新的值， 包括字面值和带版本信息的值VersionedValue
 */
public class SharedCounterDemo implements SharedCountListener {

	private static final int QTY = 5;
	private static final String PATH = "/examples/counter";

	public static void main(String[] args) throws IOException, Exception {
		final Random rand = new Random();
		SharedCounterDemo example = new SharedCounterDemo();
//		try (TestingServer server = new TestingServer()) {
		try {
     	    final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
			CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDR,  new ExponentialBackoffRetry(1000, 3));
			client.start();

			SharedCount baseCount = new SharedCount(client, PATH, 0);
			baseCount.addListener(example);
			baseCount.start();

			List<SharedCount> examples = Lists.newArrayList();
			ExecutorService service = Executors.newFixedThreadPool(QTY);
			for (int i = 0; i < QTY; ++i) {
				final SharedCount count = new SharedCount(client, PATH, 0);
				examples.add(count);
				Callable<Void> task = () -> {
					count.start();
					Thread.sleep(rand.nextInt(10000));
					System.out.println("Increment:" + count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
					return null;
				};
				service.submit(task);
			}

			service.shutdown();
			service.awaitTermination(10, TimeUnit.MINUTES);

			for (int i = 0; i < QTY; ++i) {
				examples.get(i).close();
			}
			baseCount.close();
		} finally {
			
		}
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Override
	public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
		System.out.println("State changed: " + arg1.toString());
	}

	@Override
	public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
		System.out.println("SharedCountReader to " + sharedCount.getCount() + ":::" + sharedCount.getVersionedValue().toString());
		System.out.println("Counter's value is changed to " + newCount);
	}
}
