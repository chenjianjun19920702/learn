package com.cjj.learn.desginpattern.factory.simple;

public class LoginUtil {

	public LoginUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String loginType = "password";
        String name = "name";
        String password = "password";
        Login login = LoginManager.factory(loginType);
        boolean bool = login.verify(name, password);
        if (bool) {
            /**
             * 业务逻辑
             */
        } else {
            /**
             * 业务逻辑
             */
        }
	}

}
