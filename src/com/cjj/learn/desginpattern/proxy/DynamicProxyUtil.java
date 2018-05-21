package com.cjj.learn.desginpattern.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class DynamicProxyUtil {

	public DynamicProxyUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {  
		/*RealSubject rs = new RealSubject();  
        InvocationHandler handler = new DynamicSubject(rs);  
        Class cls = rs.getClass();*/  
		
        // 以下是分解步骤  
        /*Class c = Proxy.getProxyClass(cls.getClassLoader(), cls.getInterfaces()); 
        Constructor ct = c.getConstructor(new Class[]{InvocationHandler.class}); 
        Subject subject =(Subject) ct.newInstance(new Object[]{handler}); 
        subject.request();*/
          
        // 以下是一次性生成  
        /*Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(), handler);  
        subject.request();*/   
        
		// 把上面的方法都封装进动态代理类，代码量被固定下来，不会因为业务的逐渐庞大而庞大
        DynamicSubjectBetter proxy = new DynamicSubjectBetter();	
        // 绑定该类实现的所有接口
        Subject sub = (Subject) proxy.bind(new RealSubject());	
        sub.request();
    }  

}
