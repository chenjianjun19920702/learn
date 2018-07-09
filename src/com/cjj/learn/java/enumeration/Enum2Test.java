package com.cjj.learn.java.enumeration;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class Enum2Test {

	public Enum2Test() {
		
	}

	public static void main(String[] args) {
		Enum2 enumFri = Enum2.FRI;
		System.out.println("enumFri 的 value = " + enumFri.getValue());
		System.out.println("enumFri 的 isRest = " + enumFri.isRest());
		
		Enum2 enumSun = Enum2.SUN;
		System.out.println("enumSun 的 value = " + enumSun.getValue());
		System.out.println("enumSun 的 isRest = " + enumSun.isRest());
		
		// EnumSet的使用
        EnumSet<Enum2> weekSet = EnumSet.allOf(Enum2.class);
        for (Enum2 day : weekSet) {
            System.out.println(day);
        }
        
        // EnumMap的使用
		EnumMap<Enum2, String> weekMap = new EnumMap<Enum2, String>(Enum2.class);
        weekMap.put(Enum2.MON, "星期一");
        weekMap.put(Enum2.TUE, "星期二");
        // ... ...
        for (Iterator<Entry<Enum2, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
            Entry<Enum2, String> entry = iter.next();
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
	}
}
