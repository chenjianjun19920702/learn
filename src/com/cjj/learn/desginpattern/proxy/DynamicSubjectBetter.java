package com.cjj.learn.desginpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
          作者：雨夜偷牛的人
	链接：https://www.zhihu.com/question/20794107/answer/23330381
	来源：知乎
	著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class DynamicSubjectBetter implements InvocationHandler {

	private Object sub; // 真实对象的引用  

	public DynamicSubjectBetter() {  
	}  

	/**
	 * 绑定委托对象，并返回代理类
	 */
	public Object bind(Object sub)
	{
		this.sub = sub;
		// 绑定该类实现的所有接口，取得代理类 
		return Proxy.newProxyInstance(sub.getClass().getClassLoader(),
				sub.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before calling " + method);   
		// 这里就可以进行所谓的AOP编程了
        // 在调用具体函数方法前，执行功能处理
		method.invoke(sub, args);   
		// 在调用具体函数方法后，执行功能处理
		System.out.println("after calling " + method); 
		return null;  
	}

}
