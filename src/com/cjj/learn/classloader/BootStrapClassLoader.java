package com.cjj.learn.classloader;

import java.net.URL;

/**
 * 启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库，如：rt.jar、resources.jar、charsets.jar等
 */
public class BootStrapClassLoader {
	
	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}
		
		System.out.println(System.getProperty("sun.boot.class.path"));
		
	}
}
