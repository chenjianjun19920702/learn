package com.cjj.learn.java.jedis.pushandpop;

import java.util.List;

public class RedisQuene {

	public RedisQuene() {
		// TODO Auto-generated constructor stub
	}

	public static byte[] redisKey = "key".getBytes(); 
	
	static{  
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}  
	
	private static void pop() throws Exception {  
		byte[] bytes = JedisUtil.rpop(redisKey);  
		Message msg = (Message) ObjectUtil.bytesToObject(bytes);  
		if(msg != null){  
			System.out.println(msg.toString());  
		}  
	}  

	private static void init() throws Exception {  
		/*Message msg1 = new Message(1, "内容1");  
		JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg1));  
		Message msg2 = new Message(2, "内容2");  
		JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg2));  
		Message msg3 = new Message(3, "内容3");  
		JedisUtil.lpush(redisKey, ObjectUtil.objectToBytes(msg3));*/  
	}  
	
	public static void main(String[] args) throws Exception {  
		/*try {
			pop();
		} catch (Exception e) {
			e.printStackTrace();
		}*/ 
		
		List<byte[]> res = JedisUtil.lpopList(redisKey);
		for (byte[] obj : res) {
			Message msg = (Message) ObjectUtil.bytesToObject(obj);
			System.out.println(msg.toString());
		}
	}  

}
