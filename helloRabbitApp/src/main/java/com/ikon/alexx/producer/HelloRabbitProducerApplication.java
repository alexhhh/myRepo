package com.ikon.alexx.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
 
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class HelloRabbitProducerApplication {

	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//SpringApplication.run(HelloRabbitProducerApplication.class, args);
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost"); // If connect to a different machine,
										// specify its name or IP address
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();		
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "I was wondering if after all these years";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());		
		System.out.println(" [XXX] Sent '" + message + "'");
		
		channel.close();
		connection.close();
	}
}
