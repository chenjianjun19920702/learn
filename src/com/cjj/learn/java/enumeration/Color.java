package com.cjj.learn.java.enumeration;

public enum Color implements Behaviour {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
	
	//
	private String name;
	private int index;
	
	//
	private Color(String name,int index) {
		this.setName(name);
		this.setIndex(index);
		System.out.println("constructor is running... name is " + name + ",index is " + index);
	}
	
	public static String getName(int index) {
		for (Color c : Color.values()) {
			if (index == c.getIndex()) {
				return c.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return this.index+"_"+this.name;  
	}

	@Override
	public void print() {
		System.out.println(this.index+":"+this.name);  
	}

	@Override
	public String getInfo() {
		return this.name;  
	}
	
}
