package com.cjj.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 */
public class ServiceInvocationHandler implements InvocationHandler {
	
	// 目标对象
	private Object target;
	
	public ServiceInvocationHandler(Object target) {
		super();
		this.target = target;
	}
	
	/**
     * 创建代理实例
     * @return
     * @throws Throwable
     */
    public Object getProxy() throws Throwable {
        return Proxy.newProxyInstance(Thread.currentThread()
                .getContextClassLoader(), this.target.getClass()
                .getInterfaces(), this);
        // 这样写只返回了目标对象，没有生成代理对象。
        // return target;
    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/*this.doBefore();
		// 执行目标方法对象
		Object o = method.invoke(target, args);
		this.doAfter();
		return o;*/
		
		Object result = null;
        System.out.println("代理类方法，进行了增强。。。");
        System.out.println("事务开始。。。");
        // 执行目标方法对象
        result = method.invoke(target, args);
        System.out.println("事务结束。。。");
        return result;
	}

	private void doAfter() {
		System.out.println("do this after...");
	}

	private void doBefore() {
		System.out.println("do this before...");
	}

}
