package com.cjj.learn.java.enumeration;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (EnumTest e : EnumTest.values()) {
//			System.out.println(e.toString());
			System.out.println(e.name() + "  " + e.ordinal());
		}

		System.out.println("----------------我是分隔线------------------");

		EnumTest test = EnumTest.TUE;
		switch (test) {
		case MON:
			System.out.println("今天是星期一");
			break;
		case TUE:
			System.out.println("今天是星期二");
			break;
			// ... ...
		default:
			System.out.println(test);
			break;
		}
		
		//compareTo(E o)
        switch (test.compareTo(EnumTest.MON)) {
        case -1:
            System.out.println("TUE 在 MON 之前");
            break;
        case 1:
            System.out.println("TUE 在 MON 之后");
            break;
        default:
            System.out.println("TUE 与 MON 在同一位置");
            break;
        }
         
        //getDeclaringClass()
        System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());
         
        //name() 和  toString()
        System.out.println("name(): " + test.name());
        System.out.println("toString(): " + test.toString());
         
        //ordinal()， 返回值是从 0 开始
        System.out.println("ordinal(): " + test.ordinal());
	}

}
