package com.cjj.learn.java.jedis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class JedisSingleUtil {

	public JedisSingleUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-redis.xml");

		// 获取Spring提供的RedisTemplate类此类封装了Jedis，简化操作
		RedisTemplate<String, List<String>> redisTemplate = applicationContext.getBean("jedisTemplate", RedisTemplate.class);
		// Spring 提供的各种Redis结构的key-value操作类
		ValueOperations<String, List<String>> value = redisTemplate.opsForValue();
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		ListOperations<String, List<String>> list = redisTemplate.opsForList();
		HyperLogLogOperations<String, List<String>> hyperLogLog = redisTemplate.opsForHyperLogLog();
		SetOperations<String, List<String>> set = redisTemplate.opsForSet();
		ZSetOperations<String, List<String>> zSet = redisTemplate.opsForZSet();
		List<String> listValue = new ArrayList<String>();
		listValue.add("001");
		listValue.add("002");
		value.set("list", listValue);
		System.out.println(value.get("list"));

		// 关闭Spring容器释放资源
		applicationContext.close();
	}

}
