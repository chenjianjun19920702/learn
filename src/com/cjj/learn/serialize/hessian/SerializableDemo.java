package com.cjj.learn.serialize.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 
 * 当用hessian序列化对象是一个对象继承另外一个对象的时候，当一个属性在子类和有一个相同属性的时候，反序列化后子类属性总是为null。
 * 
 *  1. hessian序列化的时候会取出对象的所有自定义属性，相同类型的属性是子类在前父类在后的顺序。
 *  2. hessian在反序列化的时候，是将对象所有属性取出来，存放在一个map中   key = 属性名  value是反序列类，相同名字的会以子类为准进行反序列化。
 *  3. 相同名字的属性 在反序列化的是时候，由于子类在父类前面，子类的属性总是会被父类的覆盖，由于java多态属性，在上述例子中父类 User.username = null。
 *
 */
public class SerializableDemo {
	
	public static void main(String[] args) throws Exception {
		UserInfo user = new UserInfo();
        user.setUsername("hello world");
        user.setPassword("buzhidao");
        user.setAge(21);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // Hessian 的序列化输出
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(user);

        byte[] userByte = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);

        // Hessian 的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        UserInfo u = (UserInfo) hi.readObject();
        System.out.println(u);
	}
}
