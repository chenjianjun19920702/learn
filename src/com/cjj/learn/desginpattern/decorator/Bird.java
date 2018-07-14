package com.cjj.learn.desginpattern.decorator;

/**
 * 具体装饰角色
 */
public class Bird extends Change {

	public Bird(TheMonkeyKing sage) {
		super(sage);
	}
	
	@Override
    public void move() {
        // 代码
        System.out.println("Bird Move");
    }

}
