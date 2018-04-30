package com.cjj.learn.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class UseClusterRedis {

	public static void main(String[] args) {
		
		String host = "10.211.55.4";
	    
	    Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
	    jedisClusterNode.add(new HostAndPort(host, 7001));
	    jedisClusterNode.add(new HostAndPort(host, 7002));
	    jedisClusterNode.add(new HostAndPort(host, 7003));
	    jedisClusterNode.add(new HostAndPort(host, 7004));
	    jedisClusterNode.add(new HostAndPort(host, 7005));
	    jedisClusterNode.add(new HostAndPort(host, 7006));
	    //GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
	    //JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
	    JedisPoolConfig cfg = new JedisPoolConfig();
	    cfg.setMaxTotal(100);
	    cfg.setMaxIdle(20);
	    cfg.setMaxWaitMillis(-1);
	    cfg.setTestOnBorrow(true); 
	    JedisCluster jc = new JedisCluster(jedisClusterNode,6000,1000,cfg);	    
	    
	    System.out.println(jc.set("age","20"));
	    System.out.println(jc.set("sex","男"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("name"));
	    System.out.println(jc.get("age"));
	    System.out.println(jc.get("sex"));
	    jc.close();
		
		
	}
	
}
