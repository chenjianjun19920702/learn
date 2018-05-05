package com.cjj.learn.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class SubscribeChildChanges {
	static final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
    static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR, SESSION_TIMEOUT));
        zkClient.subscribeChildChanges("/super", (parentPath, currentChilds) -> {
            System.out.println("parentPath：" + parentPath);
            System.out.println("currentChilds：" + currentChilds);
        });

        Thread.sleep(3000);
        zkClient.createPersistent("/super");
        Thread.sleep(1000);
        zkClient.createPersistent("/super/c1", "内容一");
        Thread.sleep(1000);
        zkClient.createPersistent("/super/c2", "内容二");
        Thread.sleep(1000);
        zkClient.delete("/super/c2");
        Thread.sleep(1000);
        zkClient.deleteRecursive("/super");
        
        Thread.sleep(Integer.MAX_VALUE);
    }
}
