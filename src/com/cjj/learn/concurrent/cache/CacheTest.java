package com.cjj.learn.concurrent.cache;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class CacheTest {
	
	private final Computable<BigInteger, BigInteger> c = 
			new Computable<BigInteger, BigInteger>() {

				@Override
				public BigInteger compute(BigInteger arg) throws InterruptedException {
					// 模拟计算
					TimeUnit.SECONDS.sleep(5);
					return arg;
				}
			};
			
	private final Computable<BigInteger, BigInteger> cache = new Cache<BigInteger, BigInteger>(c);
	
	public void cal(BigInteger bi) {
		try {
			cache.compute(bi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}

}
