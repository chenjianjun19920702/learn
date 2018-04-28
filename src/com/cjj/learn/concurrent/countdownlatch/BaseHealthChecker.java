package com.cjj.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {
	
	private CountDownLatch countDownLatch;
	
	private String serviceName;
	
	public BaseHealthChecker(CountDownLatch countDownLatch, String serviceName) {
		super();
		this.countDownLatch = countDownLatch;
		this.serviceName = serviceName;
		this.serviceUp = false;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public boolean isServiceUp() {
		return serviceUp;
	}

	public void setServiceUp(boolean serviceUp) {
		this.serviceUp = serviceUp;
	}

	private boolean serviceUp;
	
	public abstract void verifyService();

	@Override
	public void run() {
		try {
			verifyService();
			this.serviceUp = true;
		} catch(Exception e) {
			e.printStackTrace();
			this.serviceUp = false;
		} finally {
			if (countDownLatch != null) {
				countDownLatch.countDown();
			}
		}
	}

}
