package com.cjj.learn.java.enumeration;

public class ColorTest {

	public ColorTest() {
		
	}
	
	public static void main(String args[]) {
		System.out.println(Color.getName(1));
		System.out.println(Color.GREEN.toString());
		Color.BLANK.print();
		System.out.println(Color.YELLOW.getInfo());
	}
}
