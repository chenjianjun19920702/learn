package com.cjj.learn.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class ClusterTest {

	public static JedisCluster jedisCluster;
	
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}
	
	public static void main(String[] args) {
		
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext-cluster.xml");
        jedisCluster = (JedisCluster)ac.getBean("redisCluster");
        
//        for (int i=0; i<100; i++) {
//        	jedisCluster.set("name" + i, "value" + i);
//        }
//        System.out.println(jedisCluster.get("name4"));		
		
    }
}
