package com.cjj.learn.serialize;

import java.util.Date;

public class SerializableDemo {
	
	public static void main(String[] args) {
        // Initializes The Object
        User user = new User();
        user.setName("hollis");
        user.setGender("male");
        user.setAge(23);
        user.setBirthday(new Date());
        user.setIdCard("362531199901011399");
        user.setTel("13313991399");
        System.out.println(user);
        
        String strSerialize= SerialUtil.serializer(user);
        System.out.println(strSerialize);
        
        User userDeserial = (User) SerialUtil.deserializer(strSerialize);
        System.out.println(userDeserial);
	}
}
