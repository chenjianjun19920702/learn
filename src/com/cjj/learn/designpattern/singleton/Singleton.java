package com.cjj.learn.designpattern.singleton;

// 饿汉式
/*public class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
	
}*/

//懒汉式（非线程安全）
/*public class Singleton {
	
	private static Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}*/

//懒汉式（线程安全）
/*public class Singleton {
	
	private static Singleton instance;

	private Singleton() {
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}*/

//双重校验锁（Double Check Lock）
/*public class Singleton {
	
	// 注意此处使用的关键字 volatile，
	// 被volatile修饰的变量的值，将不会被本地线程缓存，
	// 所有对该变量的读写都是直接操作共享内存，从而确保多个线程能正确的处理该变量。
	private volatile static Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized(Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}*/

//静态内部类
/*public class Singleton {
	
	private Singleton() {
	}

	public static Singleton getInstance() {
		return SingletonHolder.sInstance;
	}

	public static class SingletonHolder {
		private static final Singleton sInstance = new Singleton();  
	}
}*/

public enum Singleton {
	//定义一个枚举的元素，它就是 Singleton 的一个实例
    INSTANCE;  
    
    public void doSomeThing() {  
	    System.out.println("do something...");
    }  
}
