/**
 * 
 */
package com.cjj.learn.desginpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverFirst implements Observer {

	/**
	 * 
	 */
	public ObserverFirst() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ObservableFor3D) {  
			ObservableFor3D subjectFor3d = (ObservableFor3D) o;  
            System.out.println("observableFor3D's msg -- >" + subjectFor3d.getMsg());  
        }  
  
        if (o instanceof ObservableForSSQ) {  
        	ObservableForSSQ subjectForSSQ = (ObservableForSSQ) o;  
            System.out.println("observableForSSQ's msg -- >" + subjectForSSQ.getMsg());  
        }  

	}
	
	public void registerSubject(Observable observable) {  
        observable.addObserver(this);  
    }  

}
