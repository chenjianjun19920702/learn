package com.cjj.learn.zookeeper.curator.lock;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class InterProcessSemaphoreDemo {

	private static final int MAX_LEASE = 10;
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
//        try (TestingServer server = new TestingServer()) {
       try {
    	   
    	   final String CONNECT_ADDR = "10.211.55.4:2181,10.211.55.5:2181,10.211.55.6:2181";

            CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new ExponentialBackoffRetry(1000, 3));
            client.start();

            InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, PATH, MAX_LEASE);
            Collection<Lease> leases = semaphore.acquire(5);
            System.out.println("get " + leases.size() + " leases");
            Lease lease = semaphore.acquire();
            System.out.println("get another lease");

            resource.use();

            // 这里不够了，超时机制
            Collection<Lease> leases2 = semaphore.acquire(5, 10, TimeUnit.SECONDS);
            System.out.println("Should timeout and acquire return " + leases2);

            System.out.println("return one lease");
            semaphore.returnLease(lease);
            System.out.println("return another 5 leases");
            semaphore.returnAll(leases);
            
            client.close();
        } finally {
        	
        }
    }

}
