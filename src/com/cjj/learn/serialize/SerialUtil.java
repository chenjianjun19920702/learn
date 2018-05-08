package com.cjj.learn.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;


public class SerialUtil {
	
	public static String serializer(Object object){
		String serStr = null;
		if(object==null){
			return serStr;
		}
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object); 
			
			byte[] serBytes = byteArrayOutputStream.toByteArray();
			serStr = Base64.getUrlEncoder().encodeToString(serBytes);
			
			objectOutputStream.close();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serStr;
	}
	
	public static Object deserializer(String serStr){
		Object object = null;
		if(serStr==null||"".equals(serStr.trim())){
			return object;
		}
		byte[] redBytes = Base64.getUrlDecoder().decode(serStr);
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redBytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			object = objectInputStream.readObject(); 
			objectInputStream.close();
			byteArrayInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return object;
	}
	
	public static void main(String[] args) {
		
		
	}

}
