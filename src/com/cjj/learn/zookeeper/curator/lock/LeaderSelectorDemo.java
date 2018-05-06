package com.cjj.learn.zookeeper.curator.lock;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/8 1:00
 */
public class LeaderSelectorDemo {

	protected static String PATH = "/francis/leader";
	private static final int CLIENT_QTY = 10;


	public static void main(String[] args) throws Exception {
		List<CuratorFramework> clients = Lists.newArrayList();
		List<LeaderSelectorAdapter> examples = Lists.newArrayList();
//		TestingServer server = new TestingServer();
		try {
			for (int i = 0; i < CLIENT_QTY; i++) {
				CuratorFramework client
						= CuratorFrameworkFactory.newClient("10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181", new ExponentialBackoffRetry(20000, 3));
				clients.add(client);
				LeaderSelectorAdapter selectorAdapter = new LeaderSelectorAdapter(client, PATH, "Client #" + i);
				examples.add(selectorAdapter);
				client.start();
				selectorAdapter.start();
			}
			System.out.println("Press enter/return to quit\n");
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} finally {
			System.out.println("Shutting down...");
			for (LeaderSelectorAdapter exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
//			CloseableUtils.closeQuietly(server);
		}
	}
}
