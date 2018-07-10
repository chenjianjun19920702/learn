package com.cjj.learn.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果颜色注解
 */
// 限定注解运用的场景
@Target(ElementType.FIELD)
// 注解的的存活时间
@Retention(RetentionPolicy.RUNTIME)
// 注解中的元素包含到 Javadoc 中去
@Documented
public @interface FruitColor {
	
    /**
     * 颜色枚举
     */
    public enum Color{ BULE,RED,GREEN };
    
    /**
     * 颜色属性
     * @return
     */
    // 注解的属性也叫做成员变量。注解只有成员变量，没有方法。
    // 注解的成员变量在注解的定义中以“无形参的方法”形式来声明，
    // 其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
    Color fruitColor() default Color.GREEN;
}
