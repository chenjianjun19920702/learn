package com.cjj.learn.desginpattern.adaper;

public class AdapterUtil {

	public AdapterUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {  
		Adapter1 a1 = new Adapter1();
		a1.sampleOperation1();
		a1.sampleOperation2();
		
		Adapter2 a2 = new Adapter2(new Adaptee());
		a2.sampleOperation1();
		a2.sampleOperation2();
    }  

}
