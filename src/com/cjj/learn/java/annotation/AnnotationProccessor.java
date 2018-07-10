package com.cjj.learn.java.annotation;

import java.lang.reflect.Field;

public class AnnotationProccessor {

	public AnnotationProccessor() {
	}

	public static void annoProcess(AnnotationDemo annotation) {  
		for (Field field : annotation.getClass().getDeclaredFields()) {  
			if (field.isAnnotationPresent(AnnotationTag.class)) {  // 如果存在 AnnotationTag 标签  
				AnnotationTag tag = field.getAnnotation(AnnotationTag.class); 
				annotation.setCar(new AnnotationCar(tag.name(), tag.size()));  
			}  
		}  
	}  
	
	public static void main(String[] args) {  
		AnnotationDemo ann = new AnnotationDemo();  
		annoProcess(ann);  
		System.out.println(ann.car.toString());  
	}  
}
