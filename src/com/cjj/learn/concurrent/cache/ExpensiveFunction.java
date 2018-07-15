package com.cjj.learn.concurrent.cache;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ExpensiveFunction implements Computable<String, BigInteger> {

	@Override
	public BigInteger compute(String arg) throws InterruptedException {
		// 模拟计算
		TimeUnit.SECONDS.sleep(5);
		return new BigInteger(arg);
	}

}
