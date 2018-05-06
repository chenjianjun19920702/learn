package com.cjj.learn.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TreeCacheDemo {
	
	private static final String PATH = "/example/cache";
	
	static final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";

    public static void main(String[] args) throws Exception {
    	
        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new ExponentialBackoffRetry(1000, 3));
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
