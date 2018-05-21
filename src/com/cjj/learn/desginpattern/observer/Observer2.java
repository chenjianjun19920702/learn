package com.cjj.learn.desginpattern.observer;

public class Observer2 implements Observer {

	/*private Subject subject;  
	  
    public Observer2(Subject subject) {  
        this.subject = subject;  
        subject.registerObserver(this);  
    }*/ 
	
    public Observer2() {
    	
    }
  
    @Override  
    public void update(String msg) {  
        System.out.println("observer2 得到 3D 号码  -->" + msg + ", 我要记下来。");  
    }  

}
