package com.cjj.learn.proxy;

/**
 * jdk 静态代理
 */
public class UserServiceProxy implements UserService {
 
    private UserServiceImpl userImpl;
 
    public UserServiceProxy(UserServiceImpl userImpl) {
        this.userImpl = userImpl;
    }
 
    public void addUser() {
        System.out.println("代理类方法，进行了增强。。。");
        System.out.println("事务开始。。。");
        // 调用委托类的方法
        userImpl.addUser();
        System.out.println("处理结束。。。");
    }
 
    public void editUser() {
        System.out.println("代理类方法，进行了增强。。。");
        System.out.println("事务开始。。。");
        // 调用委托类的方法
        userImpl.editUser();
        System.out.println("事务结束。。。");
    }
}
