package com.cjj.learn.desginpattern.strategy;

/** 
 * 游戏的角色超类 
 */  
public abstract class Role  
{  
	protected String name;  

	protected AttackBehaviorStrategy attackBehaviorStrategy;  
	protected DefendBehaviorStrategy defendBehaviorStrategy;  
	protected RunBehaviorStrategy runBehaviorStrategy;  
	protected DisplayBehaviorStrategy displayBehaviorStrategy;  

	public Role setDefendBehavior(DefendBehaviorStrategy defendBehaviorStrategy) {  
		this.defendBehaviorStrategy = defendBehaviorStrategy;  
		return this;  
	}  

	public Role setDisplayBehavior(DisplayBehaviorStrategy displayBehaviorStrategy) {  
		this.displayBehaviorStrategy = displayBehaviorStrategy;  
		return this;  
	}  

	public Role setRunBehavior(RunBehaviorStrategy runBehaviorStrategy) {  
		this.runBehaviorStrategy = runBehaviorStrategy;  
		return this;  
	}  

	public Role setAttackBehavior(AttackBehaviorStrategy attackBehaviorStrategy) {  
		this.attackBehaviorStrategy = attackBehaviorStrategy;  
		return this;  
	}  

	protected void display() {  
		displayBehaviorStrategy.display();  
	}  

	protected void run() {  
		runBehaviorStrategy.run();  
	}  

	protected void attack() {  
		attackBehaviorStrategy.attack();  
	}  

	protected void defend() {  
		defendBehaviorStrategy.defend();  
	}

}  
