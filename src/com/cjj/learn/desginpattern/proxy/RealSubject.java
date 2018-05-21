package com.cjj.learn.desginpattern.proxy;

/** 
 * 真实的角色 
 */
public class RealSubject implements Subject {

	public RealSubject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		System.out.println("RealSubject  request.....");
	}

}
