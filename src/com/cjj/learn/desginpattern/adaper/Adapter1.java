package com.cjj.learn.desginpattern.adaper;

/**
 * 类适配器模式:把适配的类的API转换成为目标类的API。
 */
public class Adapter1 extends Adaptee implements Target {

	public Adapter1() {
		// TODO Auto-generated constructor stub
	}

	/**
     * 由于源类Adaptee没有方法sampleOperation2()
     * 因此适配器补充上这个方法
     */
    @Override
    public void sampleOperation2() {
        // 写相关的代码
    	System.out.println("Adapter1 implements sampleOperation2.......");
    }

}
