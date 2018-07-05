package com.cjj.learn.java.jedis.pushandpop;

import java.io.Serializable;

/**
 * 定义消息类接收消息内容和设置消息的下标 
 */  
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411162746058342932L;

	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(int id, String content) {
		this.id = id;
		this.content = content;
	}

	private int id;  
	
	private String content;  
	
	public int getId() {  
		return id;  
	}  
	
	public void setId(int id) {  
		this.id = id;  
	}  
	
	public String getContent() {  
		return content;  
	}  
	
	public void setContent(String content) {  
		this.content = content;  
	}
	
	@Override
	public String toString() {
		return "id is " + id + ",content is " + content;
	}

}
