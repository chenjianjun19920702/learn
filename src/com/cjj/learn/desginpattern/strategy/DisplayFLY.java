package com.cjj.learn.desginpattern.strategy;

public class DisplayFLY implements DisplayBehaviorStrategy {

	public DisplayFLY() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		System.out.println("飞起来！");  
	}

}
