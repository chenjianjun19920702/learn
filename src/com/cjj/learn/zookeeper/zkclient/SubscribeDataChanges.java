package com.cjj.learn.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class SubscribeDataChanges {
	static final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
    static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR, SESSION_TIMEOUT));
        
        zkClient.subscribeDataChanges("/super", new IZkDataListener() {

			@Override
			public void handleDataChange(String arg0, Object arg1) throws Exception {
				System.out.println("变更节点为：" + arg0 + "，变更数据为：" + arg1);  
			}

			@Override
			public void handleDataDeleted(String arg0) throws Exception {
				System.out.println("删除的节点为：" + arg0);  
			}
        	
        });
        
        zkClient.createPersistent("/super", "123");  
        Thread.sleep(1000);  
        zkClient.writeData("/super", "456", -1);  
        Thread.sleep(1000);  
        zkClient.createPersistent("/super/c1", "789"); // 不会被监控到  
        zkClient.deleteRecursive("/super");  
        
        Thread.sleep(Integer.MAX_VALUE);
    }
}
