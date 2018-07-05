package com.cjj.learn.java.enumeration;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class Test2 {

	public Test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EnumTest2 enumFri = EnumTest2.FRI;
		System.out.println("enumFri 的 value = " + enumFri.getValue());
		System.out.println("enumFri 的 isRest = " + enumFri.isRest());
		
		EnumTest2 enumSun = EnumTest2.SUN;
		System.out.println("enumSun 的 value = " + enumSun.getValue());
		System.out.println("enumSun 的 isRest = " + enumSun.isRest());
		
		// EnumSet的使用
        EnumSet<EnumTest> weekSet = EnumSet.allOf(EnumTest.class);
        for (EnumTest day : weekSet) {
            System.out.println(day);
        }
        
        // EnumMap的使用
		EnumMap<EnumTest, String> weekMap = new EnumMap(EnumTest.class);
        weekMap.put(EnumTest.MON, "星期一");
        weekMap.put(EnumTest.TUE, "星期二");
        // ... ...
        for (Iterator<Entry<EnumTest, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
            Entry<EnumTest, String> entry = iter.next();
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
	}

}
