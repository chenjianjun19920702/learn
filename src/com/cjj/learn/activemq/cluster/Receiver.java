package com.cjj.learn.activemq.cluster;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args)  {
		try {
			//第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，均使用默认即可，默认端口为"tcp://localhost:61616"
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnectionFactory.DEFAULT_USER, 
					ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
					"tcp://10.211.55.4:61616");
//					"failover:(tcp://192.168.1.111:51511,tcp://192.168.1.111:51512,tcp://192.168.1.111:51513)?Randomize=false");
			
			//第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			//第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数配置1为是否启用是事务，参数配置2为签收模式，一般我们设置自动签收。
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			
			//第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
			Destination destination = session.createQueue("first");
			//第五步：通过Session创建MessageConsumer
			MessageConsumer consumer = session.createConsumer(destination);
			
			while(true){
				TextMessage msg = (TextMessage)consumer.receive();
				if(msg == null) break;
				System.out.println("收到的内容：" + msg.getText());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
	}
}
