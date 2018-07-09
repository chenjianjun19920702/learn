package com.cjj.learn.java.enumeration;

public enum Enum2 {
	MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6) {
        @Override
        public boolean isRest() {
            return true;
        }
    },
    SUN(0) {
        @Override
        public boolean isRest() {
            return true;
        }
    };
 
    private int value;
 
    private Enum2(int value) {
        this.value = value;
    }
 
    public int getValue() {
        return value;
    }
 
    public boolean isRest() {
        return false;
    }
    
}
