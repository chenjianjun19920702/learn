package com.cjj.learn.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String args[]) throws Throwable {
		// 静态代理
		UserServiceImpl userImpl = new UserServiceImpl();
		UserServiceProxy proxy1 = new UserServiceProxy(userImpl);
		System.out.println("----------分割线----------");
		proxy1.addUser();
		System.out.println("----------分割线----------");
		proxy1.editUser();

		/**
		 * jdk动态代理会生成一个动态代理类，生成相应的字节码，然后通过ClassLoader加载字节码。
		 * 该实例继承了Proxy类，并实现了业务接口，在实现的方法里通过反射调用了InvocationHandler接口实现类
		 * 的invoke()回调方法。
		 */
		UserService userService = new UserServiceImpl();
		ServiceInvocationHandler handler = new ServiceInvocationHandler(userService);
		// 根据目标生成代理对象
		UserService proxy2 = (UserService) handler.getProxy();
		System.out.println("----------分割线----------");
		proxy2.addUser();
		System.out.println("----------分割线----------");
		proxy2.editUser();

		CglibUserService cglib = new CglibUserService();
		CglibUserServiceImpl cglibUserImpl = (CglibUserServiceImpl) cglib.getInstance(new CglibUserServiceImpl());
		System.out.println("----------分割线----------");
		cglibUserImpl.addUser();
		System.out.println("----------分割线----------");
		cglibUserImpl.editUser();
	}
}
