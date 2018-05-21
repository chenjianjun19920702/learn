package com.cjj.learn.desginpattern.strategy;

public class StrategyUtil {

	public StrategyUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {  

		Role roleA = new RoleA("A...");  

		roleA.setAttackBehavior(new AttackJY())
			 .setDefendBehavior(new DefendJZZ())
		     .setDisplayBehavior(new DisplayPA())
		     .setRunBehavior(new RunLBWB());  
		System.out.println(roleA.name + ":");  
		roleA.run();  
		roleA.attack();  
		roleA.defend();  
		roleA.display();  
	}  

}
