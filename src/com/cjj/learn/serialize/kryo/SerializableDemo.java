package com.cjj.learn.serialize.kryo;

public class SerializableDemo {
	
	public static void main(String[] args) throws Exception {
		User user = new User();
        user.setUsername("hello world");
        user.setPassword("buzhidao");
        user.setAge(21);
        
        byte[] by = KryoUtil.writeObjectToByteArray(user);
        User u1 = KryoUtil.readObjectFromByteArray(by, User.class);
        System.out.println(u1);
        
        String s = KryoUtil.writeObjectToString(user);
        User u2 = KryoUtil.readObjectFromString(s, User.class);
        System.out.println(u2);
        
        byte[] byy = KryoUtil.writeToByteArray(user);
        User uu1 = KryoUtil.readFromByteArray(byy);
        System.out.println(uu1);
        
        String ss = KryoUtil.writeToString(user);
        User uu2 =KryoUtil.readFromString(ss);
        System.out.println(uu2);
	}
}
