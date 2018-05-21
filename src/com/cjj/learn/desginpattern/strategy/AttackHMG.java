package com.cjj.learn.desginpattern.strategy;

public class AttackHMG implements AttackBehaviorStrategy {

	public AttackHMG() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		System.out.println("蛤蟆功！");  
	}

}
