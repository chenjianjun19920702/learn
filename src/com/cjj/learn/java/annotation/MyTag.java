package com.cjj.learn.java.annotation;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy; 

@Target({ElementType.METHOD,ElementType.FIELD})  
@Inherited  
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTag {
	String name() default "车";  
    int size() default 10;
}
