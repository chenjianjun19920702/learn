package com.cjj.learn.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Cglib 动态代理
 */
public class CglibUserService implements MethodInterceptor {
	
	/**
	 * 目标对象 被代理类
	 */
	private Object target;
    
    /**
     * 创建代理实例
     */
    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        // 继承被代理类
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 实现MethodInterceptor接口要重写的方法。
     * 回调方法
     */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("事务开始。。。");   
        Object result = proxy.invokeSuper(obj, args);   
        System.out.println("事务结束。。。");   
        return result;   
	}
}
