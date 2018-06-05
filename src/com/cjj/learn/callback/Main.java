package com.cjj.learn.callback;

public class Main {
	public static void main(String[] args) {
		Li li = new Li();
		Wang wang = new Wang(li);
		wang.askQusetion("你在干什么？");
	}
}
