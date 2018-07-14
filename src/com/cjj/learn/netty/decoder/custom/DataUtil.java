package com.cjj.learn.netty.decoder.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
	
	public final static List<String> yisuoDatas = new ArrayList<String>();
	
	/*{ "task_type": 1001, "account_id": 2, "api_id": 101, "_s_": "machine name", 
		"sid": 35075595, "output": {"czrkxm": 1, "czrkgmsfhm": 1}, 
		"result_time": 1480411648 }*/
	
	public static String yisouResult = "{"
			+ "\"task_type\":1001,"
			+ "\"account_id\":12306,"
			+ "\"api_id\":12306,"
			+ "\"_s_\":\"01020304\","
			+ "\"sid\":123456789,"
			+ "\"output\":{\"sfzh\":-1,\"key\":1},"
			+ "\"result_time\":1480411648"
			+ "}";
	
	public final static String beginFlag = "{\"task_type\":";
	
	public final static String endFlagS = "},\"result_time\":";
	
	public final static String endFlagM = "1480411648";
	
	public final static String endFlagE = "}";
	
	public final static byte[] endFlagSBytes = endFlagS.getBytes();
	
	public final static byte[] endFlagMBytes = endFlagM.getBytes();
	
	public final static byte[] endFlagEBytes = endFlagE.getBytes();
	
	public final static String dity = "{cjjsdsj{{fsjfh}}sfsjgjsjfsjjg}";
	
	static {
		
		/*Map<String, Object> data = new HashMap<String, Object>();
		data.put("task_type", 1001);
		data.put("account_id", 12306);
		data.put("api_id", 12306);
		data.put("_s_", "01020304");
		data.put("sid", 123456789L);
		
		Map<String, Object> output = new HashMap<String, Object>();
		output.put("key", 1);
		output.put("sfzh", -1);
		
		data.put("output", output);
		data.put("result_time", 1480411648L);
		
		String json = JsonTransferUtil.ObjectToJson(data);
		System.out.println(json);*/
		
		for (int i=0; i<5; i++) {
			// 先给点脏数据
//			yisuoDatas.add(dity);
			yisuoDatas.add(dity+yisouResult+dity);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(yisouResult);
		
		System.out.println(yisouResult.getBytes().length);
		
		System.out.println("----------------------------");
		for (byte b : yisouResult.getBytes()) {
			System.out.println("byte is " + b + ", string is " + (char) b);
		}
		
		byte[] temp = new byte[27];
		System.out.println(temp.length);
		
		System.out.println(DataUtil.endFlagS.startsWith("},"));
		
		System.out.println("},\"result_time\":" == DataUtil.endFlagS);
		
		System.out.println(dity.length());
		
		System.out.println(beginFlag.length());
	}
}
