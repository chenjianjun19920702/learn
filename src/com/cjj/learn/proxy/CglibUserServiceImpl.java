package com.cjj.learn.proxy;

/**
 * 业务类
 * 没有实现接口
 * 如果类是 final 的，则没法生成代理对象，报错。
 * 如果方法是 final 的，代理无效
 */
public class CglibUserServiceImpl {
	
	public void addUser() {
        System.out.println("增加一个用户。。。");
    }
 
    public void editUser() {
        System.out.println("编辑一个用户。。。");
    }
}
