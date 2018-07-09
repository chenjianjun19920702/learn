package com.cjj.learn.java.enumeration;

/**
 *	所有的枚举都继承自java.lang.Enum类。
 *	由于Java 不支持多继承，所以枚举对象不能再继承其他类。
 */
public enum Color implements Behaviour {
	// 如果打算自定义自己的方法，那么必须在enum实例序列的最后添加一个分号。而且 Java 要求必须先定义 enum 实例。
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
	
	// 成员变量  
	private String name;
	private int index;
	
	// 构造方法
	private Color(String name,int index) {
		this.setName(name);
		this.setIndex(index);
		System.out.println("constructor is running... name is " + name + ",index is " + index);
	}
	
	// 普通方法
	public static String getName(int index) {
		for (Color c : Color.values()) {
			if (index == c.getIndex()) {
				return c.getName();
			}
		}
		return null;
	}

	// get set 方法  
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
	
	// 覆盖方法
	@Override
	public String toString() {
		return this.index+"_"+this.name;  
	}

	// 接口方法
	@Override
	public void print() {
		System.out.println(this.index+":"+this.name);  
	}
	
	// 接口方法
	@Override
	public String getInfo() {
		return this.name;  
	}
	
}
