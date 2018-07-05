package com.cjj.learn.java.jedis;

import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterUtil {

	public JedisClusterUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("10.10.8.62", 6379));
        nodes.add(new HostAndPort("10.10.8.62", 6380));
        nodes.add(new HostAndPort("10.10.8.62", 6381));
        nodes.add(new HostAndPort("10.10.8.62", 6382));
        nodes.add(new HostAndPort("10.10.8.62", 6383));
        nodes.add(new HostAndPort("10.10.8.62", 6384));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("cluster", "hello_world111");
        System.out.println("cluster : " + cluster.get("cluster"));
        // 关闭连接
        try {
			cluster.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cluster = null;
		}
	}

}
