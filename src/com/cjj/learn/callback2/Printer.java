package com.cjj.learn.callback2;

public class Printer {
	public void print(Callback callback, String text) {
		System.out.println("正在打印 ..." + text);
		try {
			Thread.currentThread();
			Thread.sleep(3000);// 毫秒
		} catch (Exception e) {
			
		}
		callback.printFinished("打印完成..." + text);
	}
}
