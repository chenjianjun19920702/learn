package com.cjj.learn.java.annotation;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy; 

// 限定注解运用的场景
@Target({ElementType.METHOD,ElementType.FIELD})  
// 注解可被继承
@Inherited  
// 注解的的存活时间
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTag {
	// 注解的属性也叫做成员变量
	String name() default "车";  
	// 注解的属性也叫做成员变量
    int size() default 10;
}
