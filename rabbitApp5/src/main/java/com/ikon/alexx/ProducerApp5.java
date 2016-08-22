package com.ikon.alexx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class ProducerApp5 implements Runnable {
 
	private static final String EXCHANGE_NAME = "exchange-test"; 
	private static final String ROUTING_KEY= "key";
	
	public void run() {
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			Connection conn = connectionFactory.newConnection();
			Channel ch = conn.createChannel();
			ch.exchangeDeclare(EXCHANGE_NAME, "direct");
			
		//	ch.confirmSelect(); // Enables publisher ack on this channel.

			String message = "I was wondering if after all these years";
			ch.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_BASIC, message.getBytes());
			System.out.println(" [X] Sent '" + message + "'");
			
		//	ch.waitForConfirmsOrDie();

			ch.close();
			conn.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
