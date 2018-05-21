package com.cjj.learn.desginpattern.proxy;

public class StaticProxyUtil {

	public StaticProxyUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {  
        Subject subject = new StaticProxySubject();  
        subject.request();  //代理者代替真实者做事情  
    }  

}
