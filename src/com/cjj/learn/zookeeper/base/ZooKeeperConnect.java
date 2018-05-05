package com.cjj.learn.zookeeper.base;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperConnect {
	
    /**
     * server列表, 以逗号分割
     */
    protected String hosts = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";
    
    /**
     * 连接的超时时间, 毫秒
     */
    private static final int SESSION_TIMEOUT = 5000;
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    protected ZooKeeper zk;

    /**
     * 连接zookeeper server
     */
    public ZooKeeper connect() throws Exception {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, new ConnWatcher());
        // 等待连接完成
        connectedSignal.await();
        return zk;
    }
    
    /**
     * 关闭zookeeper server
     */
    public void close() {
    	if (zk != null) {
			try {
				zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }

    public class ConnWatcher implements Watcher {
        public void process(WatchedEvent event) {
            // 连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected
            if (event.getState() == KeeperState.SyncConnected) {
            	if (event.getType() == EventType.None) {
                    // 放开闸门, wait在connect方法上的线程将被唤醒
                    connectedSignal.countDown();
            	}
            }
            System.out.println("...");
			System.out.println("...");
            System.out.println("节点触发了 " + event.getPath() + " 事件！");  
            System.out.println("已经触发了 " + event.getType() + " 事件！");  
            System.out.println("...");
			System.out.println("...");
        }
    }
    
    public static void main(String[] main) {
    	ZooKeeperConnect zkConn = new ZooKeeperConnect();
    	ZooKeeper zk = null;
		try {
			zk = zkConn.connect();
			String res = null;
			
			res = zk.create("/zktest", "zktest".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(res);
			
			res = zk.create("/zktest/a", "zktest/a1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(res);
			
			res = zk.create("/zktest/b", "zktest/b1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(res);
			
			List<String> childs = zk.getChildren("/zktest", false);
			for (String child : childs) {
				System.out.println(child);
				System.out.println(new String(zk.getData("/zktest/" + child, false, null)));
			}
			
			System.out.println(new String(zk.getData("/zktest", true, null)));
			System.out.println(zk.setData("/zktest", "zktest_modify".getBytes(), -1));
			
			zk.delete("/zktest/a", -1);
			zk.delete("/zktest/b", -1);
			zk.delete("/zktest", -1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zk != null) {
				try {
					zk.close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
}
