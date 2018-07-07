package com.cjj.learn.java.jedis.pushandpop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisDataTest {
	
	private void out(Object msg) {
		System.out.println(msg);
	}

	@Test
	public void testString() throws Exception {
		JedisUtil.set("name".getBytes(), "cjj".getBytes(), 1);
		Thread.sleep(1000*2);
		out(JedisUtil.get("name"));
		
		JedisUtil.set("name2".getBytes(), "cjj2".getBytes());
		out(JedisUtil.get("name2"));
		JedisUtil.del("name2".getBytes());
		out(JedisUtil.get("name2"));
		
		// 统计次数
		JedisUtil.set("age".getBytes(), "20".getBytes());
		out(JedisUtil.get("age"));
		JedisUtil.incr("age");
		out(JedisUtil.get("age"));
		JedisUtil.decr("age");
		out(JedisUtil.get("age"));
	}
	
	@Test
	public void testHashMap() throws Exception {
		// 如：set u001 "李三,18,20010101"
		// 1 key:id value:对象序列化
		// 如：mset user:001:name "李三 "user:001:age18 user:001:birthday "20010101"
		// 2 key:id+属性名 内存浪费
		// 如：hmset user:001 name "李三" age 18 birthday "20010101"   
		
		Map<String,String> user001 = new HashMap<String,String>();
		user001.put("name", "name001");
		user001.put("age", "age001");
		JedisUtil.hmset("001", user001);
		List<String> strs1 = JedisUtil.hmget("001", "name", "age");
		out(strs1);
		
		Map<String,String> user002 = new HashMap<String,String>();
		user002.put("name", "name002");
		user002.put("age", "age002");
		JedisUtil.hmset("002", user002);
		List<String> strs2 = JedisUtil.hmget("002", "name", "age");
		out(strs2);
		
		Map<byte[],byte[]> res = JedisUtil.hgetAll("001".getBytes());
		for (byte[] key : res.keySet()) {
			out(new String(key));
			out(new String(res.get(key)));
		}
	}
	
	@Test
	public void testKeys() throws Exception {
		Set<String> strs = JedisUtil.keys("*0*");
		out(strs);
	}
	
	@Test
	public void testScan() throws Exception {
		ScanParams params = new ScanParams();
		params.match("*0*");
		ScanResult<String> sr = JedisUtil.scan("0", params);
		out(sr.getStringCursor());
		out(sr.getResult());
		out(new String(sr.getCursorAsBytes()));
	}
}
