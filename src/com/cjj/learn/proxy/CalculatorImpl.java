package com.cjj.learn.proxy;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		System.out.println("calculate " + a + " plus " + b);
		return a+b;
	}

	@Override
	public int del(int a, int b) {
		System.out.println("calculate " + a + " del " + b);
		return a-b;
	}

}
