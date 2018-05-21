package com.cjj.learn.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

// 使用容器，多种单例类统一管理
public class SingletonManager {

	private static Map<String, Object> objMap = new HashMap<String,Object>();

	private SingletonManager() {
	}

	public static void registerService(String key, Object instance) {
		if (!objMap.containsKey(key)) {
			objMap.put(key, instance);
		}
	}

	public static Object getService(String key) {
		return objMap.get(key) ;
	}

}
