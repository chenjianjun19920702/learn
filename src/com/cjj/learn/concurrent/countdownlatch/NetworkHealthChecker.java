package com.cjj.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker {

	public NetworkHealthChecker(CountDownLatch countDownLatch, String serviceName) {
		super(countDownLatch, serviceName);
	}

	@Override
	public void verifyService() {
		try {
			// 模拟检测
			System.out.println("Checking " + this.getServiceName());
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}

}
