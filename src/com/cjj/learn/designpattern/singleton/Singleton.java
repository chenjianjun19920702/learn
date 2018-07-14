package com.cjj.learn.designpattern.singleton;

// 饿汉式
// 典型的空间换时间
// 会在类装载的时候就初始化对象，不管你需不需要，静态内部类可以解决这个问题
/*public class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
	
}*/

// 懒汉式（非线程安全）
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

// 懒汉式（线程安全）
// 典型的时间换空间
// 每次取实例的时候都要同步，耗费时间
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

// 双重校验锁（Double Check Lock）
// 它只是第一次创建实例的时候同步，以后就不需要同步了，从而加快了运行速度
/*public class Singleton {
	
	// 注意此处使用的关键字 volatile，java5及以上的版本。
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

/**
 * 多线程缺省同步锁的知识
　　大家都知道，在多线程开发中，为了解决并发问题，主要是通过使用synchronized来加互斥锁进行同步控制。
   但是在某些情况中，JVM已经隐含地为您执行了同步，这些情况下就不用自己再来进行同步控制了。这些情况包括：
　　1.由静态初始化器（在静态字段上或static{}块中的初始化器）初始化数据时
　　2.访问final字段时
　　3.在创建线程之前创建对象时
　　4.线程可以看见它将要处理的对象时
 */
// 静态内部类
// 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
// 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
/*public class Singleton {
	
	private Singleton() {
	}

	public static Singleton getInstance() {
		return SingletonHolder.sInstance;
	}

	public static class SingletonHolder {
        // 静态初始化器，由JVM来保证线程安全
		private static final Singleton sInstance = new Singleton();  
	}
}*/

// 使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，
// 并由JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式
public enum Singleton {
	// 定义一个枚举的元素，它就是 Singleton 的一个实例
    INSTANCE;  
    
    public void doSomeThing() {  
	    System.out.println("do something...");
    }  
}
