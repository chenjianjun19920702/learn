package com.cjj.learn.concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {
	
	// List of service checkers
    private static List<BaseHealthChecker> _services;
 
    // This latch will be used to wait on
    private static CountDownLatch _latch;
	
	// 静态内部类  
    private static class NestClass {  
        private static ApplicationStartupUtil instance;  
        static {  
            System.out.println("instance = new ApplicationStartupUtil()");  
            instance = new ApplicationStartupUtil();  
        }  
    }  
      
    // 不能直接new  
    private ApplicationStartupUtil() {  
        System.out.println("private ApplicationStartupUtil()");  
    }  
      
    public static ApplicationStartupUtil getInstance() {  
        System.out.println("ApplicationStartupUtil getInstance()");  
        return NestClass.instance;  
    } 
	
	public static boolean checkExternalService() throws Exception {
		// Initialize the latch with number of service checkers
		_latch = new CountDownLatch(3);
		
		// All add checker in lists
		_services = new ArrayList<BaseHealthChecker>();
		_services.add(new NetworkHealthChecker(_latch, "Network Service"));
		_services.add(new DatabaseHealthChecker(_latch, "Database Service"));
		_services.add(new CacheHealthChecker(_latch, "Cache Service"));
		
		// Start service checkers using executor framework
		ExecutorService service = Executors.newFixedThreadPool(_services.size());
		for (BaseHealthChecker checker : _services) {
//			service.submit(checker);
			service.execute(checker);
		}
		
		// Now wait till all services are checked
        _latch.await();
        
        service.shutdown();
 
        // Services are file and now proceed startup
        for(final BaseHealthChecker v : _services) {
            if(!v.isServiceUp()) {
                return false;
            }
        }
        return true;
	}

}
