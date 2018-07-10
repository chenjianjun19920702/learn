package com.cjj.learn.java.annotation;

public class AnnotationDemo {

	@AnnotationTag(name = "audi", size = 10)  
    public AnnotationCar car;  
  
    public AnnotationCar getCar() {  
        return car;  
    }  
  
    public void setCar(AnnotationCar car) {  
        this.car = car;  
    }  
  
    @Override  
    public String toString() {  
        return "Annotation [car=" + car + "]";  
    }  

}
