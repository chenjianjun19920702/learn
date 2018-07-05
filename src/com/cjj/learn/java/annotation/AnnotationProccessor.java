package com.cjj.learn.java.annotation;

import java.lang.reflect.Field;
import java.text.DateFormat;

public class AnnotationProccessor {

	public AnnotationProccessor() {
	}

	public static void annoProcess(AnnotationDemo annotation) {  
		for (Field field : annotation.getClass().getDeclaredFields()) {  
			if (field.isAnnotationPresent(MyTag.class)) {  //如果存在MyTag标签  
				MyTag myTag = field.getAnnotation(MyTag.class); 
				annotation.setCar(new Car(myTag.name(),myTag.size()));  
			}  
		}  
	}  
	
	public static void main(String[] args) {  
		AnnotationDemo ann = new AnnotationDemo();  
//		annoProcess(ann);  
		System.out.println(ann.car.getName());  
	}  

}
