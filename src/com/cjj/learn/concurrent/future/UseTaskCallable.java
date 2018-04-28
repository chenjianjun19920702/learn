package com.cjj.learn.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class UseTaskCallable {
	public static void main(String[] args) throws Exception {
		// 构造 FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了 Callable 接口的类
		FutureTask<Object> future = new FutureTask<Object>(new TaskCallable(1, "cjj"));
		// 创建一个固定线程的线程池且线程数为1
		ExecutorService service = Executors.newFixedThreadPool(1);
		// 这里提交任务 future, 则开启线程执行 call() 方法执行
		service.submit(future);
		
		System.out.println("提交成功。。。");
		try {
			// 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("主线程执行其他操作。。。。");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
		System.out.println("执行完成，返回数据：" + future.get());
		service.shutdown();
	}
}
