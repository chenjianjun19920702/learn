package com.cjj.learn.desginpattern.decorator;

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
