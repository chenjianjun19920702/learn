package com.cjj.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {
	
	private Object obj;
	
	public LogHandler(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.doBefore();
		Object o = method.invoke(obj, args);
		this.doAfter();
		return o;
	}

	private void doAfter() {
		System.out.println("do this after...");
	}

	private void doBefore() {
		System.out.println("do this before...");
	}

}
