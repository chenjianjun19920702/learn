package com.cjj.learn.proxy;

public class CalculatorStaticProxy implements Calculator {

	private Calculator alculator;

	public CalculatorStaticProxy(Calculator alculator) {
		super();
		this.alculator = alculator;
	}

	@Override
	public int add(int a, int b) {
		// 具体执行前可以做的工作
		System.out.println("do this before...");
		// 调用委托类的方法
		int result = alculator.add(a, b);	// 调用真正实现方法
		// 具体执行后可以做的工作
		System.out.println("do this after...");
		return result;
	}

	@Override
	public int del(int a, int b) {
		// 具体执行前可以做的工作
		System.out.println("do this before...");
		// 调用委托类的方法
		int result = alculator.del(a, b);	// 调用真正实现方法
		// 具体执行后可以做的工作
		System.out.println("do this after...");
		return result;
	}

}
