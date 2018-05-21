package com.cjj.learn.desginpattern.observer;

public class ObserverUtil {

	public ObserverUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// 模拟一个3D的服务号  
        SubjectFor3D subjectFor3d = new SubjectFor3D();  
        // 客户1,2
        Observer observer1 = new Observer1();  
        Observer observer2 = new Observer2(); 
        subjectFor3d.registerObserver(observer1);
        subjectFor3d.registerObserver(observer2);
  
        subjectFor3d.setMsg("20140420的3D号码是：127" );  
        subjectFor3d.setMsg("20140421的3D号码是：333" ); 
        
        System.out.println("***********************************************************");  
        
        // 使用Java内置的类实现观察者模式
        ObservableFor3D observableFor3D = new ObservableFor3D() ;  
        ObservableForSSQ observableForSSQ = new ObservableForSSQ() ;  
        
        // 给观察者订阅服务
        ObserverFirst observerFirst = new ObserverFirst();  
        observerFirst.registerSubject(observableFor3D);  
        observerFirst.registerSubject(observableForSSQ);  
          
        observableFor3D.setMsg("hello 3d'nums : 110 ");  
        observableForSSQ.setMsg("ssq'nums : 12,13,31,5,4,3 15");
	}

}
