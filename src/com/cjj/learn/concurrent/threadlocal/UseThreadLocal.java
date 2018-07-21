package com.cjj.learn.concurrent.threadlocal;

public class UseThreadLocal {
	
	public static class MyRunnable implements Runnable {
		
		private ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
		
		private int value = 1;

		@Override
		public void run() {
			System.out.println("-------begin--------");
			threadLocal.set((int) (Math.random() * 100D));
			System.out.println("value " + value);
			value = (int) (Math.random() * 100D);
			try {
				System.out.println(threadLocal.get());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadLocal.get());
			System.out.println("value " + value);
			System.out.println("-------end--------");
		}
	}
	
	public static void main(String[] args) {
		
		/**
		 * 创建了一个MyRunnable实例，并将该实例作为参数传递给两个线程。
		 * 两个线程分别执行run()方法，并且都在ThreadLocal实例上保存了不同的值。
		 * 如果它们访问的不是ThreadLocal对象并且调用的set()方法被同步了，则第二个线程会覆盖掉第一个线程设置的值。
		 * 但是，由于它们访问的是一个ThreadLocal对象，因此这两个线程都无法看到对方保存的值。
		 * 也就是说，它们存取的是两个不同的值
		 */
		MyRunnable sharedRunnableInstance = new MyRunnable();
		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);
		thread1.run();
		thread2.run();
	}
}
