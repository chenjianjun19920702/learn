package com.cjj.learn.desginpattern.decorator;

/**
 *	具体构件角色
 */
public class Monkey implements TheMonkeyKing {

	public Monkey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		//代码
        System.out.println("Monkey Move");
	}

}
