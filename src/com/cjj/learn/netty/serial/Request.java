package com.cjj.learn.netty.serial;

import java.io.Serializable;

public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 278362468603991794L;

	private String id ;
	private String name ;
	private String requestMessage ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}


}
