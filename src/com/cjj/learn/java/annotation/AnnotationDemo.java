package com.cjj.learn.java.annotation;

public class AnnotationDemo {

	@MyTag(name = "audi", size = 10)  
    public Car car;  
  
    public Car getCar() {  
        return car;  
    }  
  
    public void setCar(Car car) {  
        this.car = car;  
    }  
  
    @Override  
    public String toString() {  
        return "Annotation [car=" + car + "]";  
    }  

}
