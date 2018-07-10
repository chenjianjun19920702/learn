package com.cjj.learn.proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {

	public static void main(String args[]) throws Throwable {
		// 静态代理
		CalculatorImpl subject = new CalculatorImpl();
		CalculatorStaticProxy p = new CalculatorStaticProxy(subject);
        int st1 = p.add(1, 1);
        System.out.println("result is " + st1);
        
        int st2 = p.del(1, 1);
        System.out.println("result is " + st2);
        
        // 动态代理
        Calculator calculator = new CalculatorImpl();
        ServiceInvocationHandler lh = new ServiceInvocationHandler(calculator);
        /*Calculator proxy = (Calculator) Proxy.newProxyInstance(
								    		calculator.getClass().getClassLoader(), 
								    		calculator.getClass().getInterfaces(), 
								    		lh);*/
        Calculator proxy = (Calculator) lh.getProxy();
        int dt1 = proxy.add(1, 2);
        System.out.println("result is " + dt1);
        
        int dt2 = proxy.del(1, 2);
        System.out.println("result is " + dt2);
    }

}
