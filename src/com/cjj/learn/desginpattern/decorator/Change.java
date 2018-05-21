package com.cjj.learn.desginpattern.decorator;

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
