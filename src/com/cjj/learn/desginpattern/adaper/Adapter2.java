package com.cjj.learn.desginpattern.adaper;

/**
 * 对象适配器模式:与类的适配器模式一样，把被适配的类的API转换成为目标类的API，
 * 与类的适配器模式不同的是，对象的适配器模式不是使用继承关系连接到Adaptee类，
 * 而是使用委派关系连接到Adaptee类。
 */
public class Adapter2 {

private Adaptee adaptee;
    
    public Adapter2(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    
    /**
     * 源类Adaptee有方法sampleOperation1
     * 因此适配器类直接委派即可
     */
    public void sampleOperation1(){
        this.adaptee.sampleOperation1();
    }
    
	/**
     * 由于源类Adaptee没有方法sampleOperation2()
     * 因此适配器补充上这个方法
     */
    public void sampleOperation2() {
        // 写相关的代码
    	System.out.println("Adapter2 implements sampleOperation2.......");
    }

}
