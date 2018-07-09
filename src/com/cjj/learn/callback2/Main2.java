package com.cjj.learn.callback2;

// 测试入口类
public class Main2 {
	
	public static void main(String[] args) {
		People people = new People();
		Callback callback = new Callback() {
			@Override
			public void printFinished(String msg) {
				System.out.println("打印机告诉我的消息是 ---> " + msg);
			}
		};
		System.out.println("需要打印的内容是 ---> " + "打印一份简历");
		people.goToPrintSyn(callback, "我的简历");
		System.out.println("我在等待 打印机 给我打印");
	}
}
