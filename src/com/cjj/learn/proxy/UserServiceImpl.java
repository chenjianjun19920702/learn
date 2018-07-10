package com.cjj.learn.proxy;

/**
 * 业务实现类
 * @author pc
 *
 */
public class UserServiceImpl implements UserService {
 
    public void addUser() {
        System.out.println("增加一个用户。。。");
    }
 
    public void editUser() {
        System.out.println("编辑一个用户。。。");
    }
}
