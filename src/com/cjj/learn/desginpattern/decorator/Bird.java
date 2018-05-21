package com.cjj.learn.desginpattern.decorator;

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
