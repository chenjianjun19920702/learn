package com.cjj.learn.java.annotation;

public class Car {

	private String name;  
    private int size;  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getSize() {  
        return size;  
    }  
    public void setSize(int size) {  
        this.size = size;  
    }  
      
    public Car(){  
          
    }  
    
    public Car(String name, int size){  
        this.size = size;  
        this.name = name;  
    }  
    
    @Override  
    public String toString() {  
        return "Car [name=" + name + ", size=" + size + "]";  
    }  

}
