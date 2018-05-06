package com.cjj.learn.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/11 22:52
 */
public class TreeCacheDemo {

	private static final String PATH = "/example/cache";

	public static void main(String[] args) throws Exception {
//		TestingServer server = new TestingServer();
		CuratorFramework client = CuratorFrameworkFactory.newClient("10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181", new ExponentialBackoffRetry(1000, 3));
		client.start();
		client.create().creatingParentsIfNeeded().forPath(PATH);
		TreeCache cache = new TreeCache(client, PATH);
		TreeCacheListener listener = (client1, event) ->
				System.out.println("事件类型：" + event.getType() +
						" | 路径：" + (null != event.getData() ? event.getData().getPath() : null));
		cache.getListenable().addListener(listener);
		cache.start();
		client.setData().forPath(PATH, "01".getBytes());
		Thread.sleep(100);
		client.setData().forPath(PATH, "02".getBytes());
		Thread.sleep(100);
		client.delete().deletingChildrenIfNeeded().forPath(PATH);
		Thread.sleep(1000 * 2);
		cache.close();
		client.close();
		System.out.println("OK!");
	}
}
