package com.cjj.learn.desginpattern.decorator;

/**
 *	具体装饰角色
 */
public class Fish extends Change {

	public Fish(TheMonkeyKing sage) {
		super(sage);
	}
	
	@Override
    public void move() {
        // 代码
        System.out.println("Fish Move");
    }

}
