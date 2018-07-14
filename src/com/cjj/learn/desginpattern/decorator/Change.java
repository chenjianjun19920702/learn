package com.cjj.learn.desginpattern.decorator;

/**
 * 装饰角色
 */
public class Change implements TheMonkeyKing {
	
	private TheMonkeyKing sage;
    
    public Change(TheMonkeyKing sage){
        this.sage = sage;
    }
    
    @Override
    public void move() {
        // 代码
        sage.move();
    }

}
