package com.cjj.learn.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
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
 * @since 2017/5/13 13:04
 */
public class DistributedDoubleBarrierDemo {

	private static final int QTY = 5;
	private static final String PATH = "/examples/barrier";

	public static void main(String[] args) throws Exception {
//		try (TestingServer server = new TestingServer()) {
		try {
			CuratorFramework client = CuratorFrameworkFactory.newClient("10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181", new ExponentialBackoffRetry(1000, 3));
			client.start();
			ExecutorService service = Executors.newFixedThreadPool(QTY);
			for (int i = 0; i < QTY; ++i) {
				/**
				 * memberQty是成员数量，当enter方法被调用时，成员被阻塞，直到所有的成员都调用了enter。 
				 * 当leave方法被调用时，它也阻塞调用线程， 知道所有的成员都调用了leave。
				 * 就像百米赛跑比赛， 发令枪响， 所有的运动员开始跑，等所有的运动员跑过终点线，比赛才结束。
				 */
				final DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, PATH, QTY);
				final int index = i;
				Callable<Void> task = () -> {

					Thread.sleep((long) (3 * Math.random()));
					System.out.println("Client #" + index + " enters");
					barrier.enter();
					System.out.println("Client #" + index + " begins");
					Thread.sleep((long) (3000 * Math.random()));
					barrier.leave();
					System.out.println("Client #" + index + " left");
					return null;
				};
				service.submit(task);
			}

			service.shutdown();
			service.awaitTermination(10, TimeUnit.MINUTES);
			Thread.sleep(Integer.MAX_VALUE);
		} finally {
			
		}
	}

}
