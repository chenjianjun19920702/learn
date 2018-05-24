package com.cjj.learn.reflect;

import java.lang.reflect.Field;

public class UseReflect2 {

	public UseReflect2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException {
		
	    User u = new User(); 
	    u.setAge(12); 					// set 
	    System.out.println(u.getAge()); // get  
	              
	    // 获取类  
	    Class c = Class.forName("com.cjj.learn.reflect.User");  
	    // 获取id属性  
	    Field ageF = c.getDeclaredField("age");  
	    // 实例化这个类赋给o  
	    Object o = c.newInstance();  
	    // 打破封装  
	    ageF.setAccessible(true); // 使用反射机制可以打破封装性，导致了java对象的属性不安全。  
	    // 给o对象的id属性赋值"110"  
	    ageF.set(o, 110); 		  // set  
	    // get  
	    System.out.println(ageF.get(o));  
	}
}
