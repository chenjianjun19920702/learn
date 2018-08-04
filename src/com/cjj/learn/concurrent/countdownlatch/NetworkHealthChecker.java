package com.cjj.learn.concurrent.countdownlatch;

import java.util.Random;
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
			Thread.sleep(new Random().nextInt(5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}
}
