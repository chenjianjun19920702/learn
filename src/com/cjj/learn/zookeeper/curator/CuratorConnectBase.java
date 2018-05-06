package com.cjj.learn.zookeeper.curator;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorConnectBase {
	
	static final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
	
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		
		CuratorFramework client = CuratorFrameworkFactory.newClient(
				CONNECT_ADDR,5000,3000,retryPolicy);
		
		client.start();
		
		String res = null;
		
		res = client.create().
				creatingParentsIfNeeded().
				withMode(CreateMode.EPHEMERAL).
				forPath("/super/a", "123456".getBytes());
		
		System.out.println(res);
		
		client.delete().deletingChildrenIfNeeded().forPath("/super/a");
		
		res = client.create().
				creatingParentsIfNeeded().
				withMode(CreateMode.EPHEMERAL).
				forPath("/super/a", "aaaaaa".getBytes());
		
		System.out.println(res);
		
		res = client.create().
				creatingParentsIfNeeded().
				withMode(CreateMode.EPHEMERAL).
				forPath("/super/b", "bbbbbb".getBytes());
		
		System.out.println(res);
		
		System.out.println(new String(client.getData().forPath("/super/a"), "utf-8"));
		
		Stat stat = new Stat();
		client.getData().storingStatIn(stat).forPath("/super/a");
		System.out.println(stat);
		
		client.setData().forPath("/super/a", "aaaa1111".getBytes());
		
		System.out.println(client.checkExists().forPath("/super/a"));

		System.out.println(client.getChildren().forPath("/super"));
		
		client.inTransaction()
		.create().withMode(CreateMode.PERSISTENT).forPath("/path","data".getBytes())
	      .and()
	      .setData().forPath("/path","data2".getBytes())
	      .and()
	      .commit();
		
		Executor executor = Executors.newCachedThreadPool();
		res = client.create().
				creatingParentsIfNeeded().
				withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {

					@Override
					public void processResult(CuratorFramework arg0, CuratorEvent arg1) throws Exception {
						System.out.println(String.format("eventType:%s,resultCode:%s",arg1.getType(),arg1.getResultCode()));
					}
					
				}, executor).
				forPath("/super/c", "123456".getBytes());
		
		System.out.println(res);
		
		client.setData().forPath("/super/c", "ccc1111".getBytes());
		
		Thread.sleep(5000);
		
		client.delete().deletingChildrenIfNeeded().forPath("/super");
		
		client.delete().deletingChildrenIfNeeded().forPath("/path");
		
		Thread.sleep(5000);

		client.close();

	}

}
