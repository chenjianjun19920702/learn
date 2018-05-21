package com.cjj.learn.desginpattern.strategy;

public class DisplayPA implements DisplayBehaviorStrategy {

	public DisplayPA() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		System.out.println("爬！");  
	}

}
