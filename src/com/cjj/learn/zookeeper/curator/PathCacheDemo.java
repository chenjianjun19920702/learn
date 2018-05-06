package com.cjj.learn.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class PathCacheDemo {
	
	private static final String PATH = "/example/pathCache";
	
	static final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";

    public static void main(String[] args) throws Exception {
    	
        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new ExponentialBackoffRetry(1000, 3));
        client.start();
        
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();
        
        PathChildrenCacheListener cacheListener = (client1, event) -> {
            System.out.println("事件类型：" + event.getType());
            if (null != event.getData()) {
                System.out.println("节点数据：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(cacheListener);
        
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        Thread.sleep(1000);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());
        Thread.sleep(1000);
        
        client.setData().forPath("/example/pathCache/test01", "01_V2".getBytes());
        client.setData().forPath("/example/pathCache/test02", "02_V2".getBytes());
        Thread.sleep(1000);
        
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
        
        client.delete().forPath("/example/pathCache/test01");
        Thread.sleep(1000);
        client.delete().forPath("/example/pathCache/test02");
        Thread.sleep(1000);
        
        cache.close();
        client.close();
        System.out.println("OK!");
    }

}
