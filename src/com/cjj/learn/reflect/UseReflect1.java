package com.cjj.learn.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class UseReflect1 {

	public UseReflect1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		// 第一种方式：  
		Class c1 = null;
		try {
			c1 = Class.forName("com.cjj.learn.reflect.User");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}  
		// 第二种方式：  
		// java中每个类型都有class 属性.  
		Class c2 = User.class;  
		   
		// 第三种方式：  
		// java语言中任何一个java对象都有getClass 方法  
		User e = new User();  
		Class c3 = e.getClass(); // c3是运行时类 (e的运行时类是User) 
		
		System.out.println(c1.newInstance().toString());
		System.out.println(c2.newInstance().toString());  
		System.out.println(c3.newInstance().toString());  
	}
}
