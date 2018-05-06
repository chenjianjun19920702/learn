package com.cjj.learn.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/13 12:53
 */
public class DistributedBarrierDemo {

	private static final int QTY = 5;
	private static final String PATH = "/examples/barrier";

	public static void main(String[] args) throws Exception {
//		try (TestingServer server = new TestingServer()) {
		try {
			CuratorFramework client = CuratorFrameworkFactory.newClient("10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181", new ExponentialBackoffRetry(1000, 3));
			client.start();
			ExecutorService service = Executors.newFixedThreadPool(QTY);
			DistributedBarrier controlBarrier = new DistributedBarrier(client, PATH);
			controlBarrier.setBarrier();

			for (int i = 0; i < QTY; ++i) {
				final DistributedBarrier barrier = new DistributedBarrier(client, PATH);
				final int index = i;
				Callable<Void> task = () -> {
					Thread.sleep((long) (3 * Math.random()));
					System.out.println("Client #" + index + " waits on Barrier");
					barrier.waitOnBarrier();
					System.out.println("Client #" + index + " begins");
					return null;
				};
				service.submit(task);
			}
			Thread.sleep(5000);
			System.out.println("all Barrier instances should wait the condition");
			controlBarrier.removeBarrier();
			service.shutdown();
			service.awaitTermination(10, TimeUnit.MINUTES);

			Thread.sleep(2000);
		} finally {
			
		}
	}
}
