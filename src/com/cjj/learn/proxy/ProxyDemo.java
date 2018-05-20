package com.cjj.learn.proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {

	public static void main(String args[]) {
		// 静态代理
		CalculatorImpl subject = new CalculatorImpl();
		CalculatorStaticProxy p = new CalculatorStaticProxy(subject);
        int st = p.add(1, 1);
        System.out.println(st);
        
        // 动态代理
        Calculator calculator = new CalculatorImpl();
        LogHandler lh = new LogHandler(calculator);
        Calculator proxy = (Calculator) Proxy.newProxyInstance(
								    		calculator.getClass().getClassLoader(), 
								    		calculator.getClass().getInterfaces(), 
								    		lh);
        int dt = proxy.add(1, 2);
        System.out.println(dt);
    }

}
