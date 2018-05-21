package com.cjj.learn.desginpattern.observer;

import java.util.Observable;

public class ObservableFor3D extends Observable {

	public ObservableFor3D() {
		// TODO Auto-generated constructor stub
	}
	
	private String msg ;   
    
    
    public String getMsg() {  
        return msg;  
    }  
  
  
    /** 
     * 主题更新消息 
     */  
    public void setMsg(String msg) {  
        this.msg = msg  ;  
        setChanged();  
        notifyObservers();  
    }  
    
}
