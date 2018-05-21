package com.cjj.learn.designpattern.singleton;

public class SingletonUtil {

	public SingletonUtil() {
	}

	public static void main(String args[]) {
		Singleton singleton1 = Singleton.INSTANCE;
		Singleton singleton2 = Singleton.INSTANCE;
		singleton1.doSomeThing();
		singleton2.doSomeThing();
		
		SingletonManager.registerService("singleton1", singleton1);
		SingletonManager.registerService("singleton2", singleton2);
		
		if (SingletonManager.getService("singleton1").equals(SingletonManager.getService("singleton2"))) {
			System.out.println("the some instance...");
		}
	}
}
