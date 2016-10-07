package com.ikon.alexx.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
 
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import com.rabbitmq.client.*;

@SpringBootApplication
public class HelloRabbitConsumerApplication {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, TimeoutException {
		 //SpringApplication.run(HelloRabbitConsumerApplication.class, args);
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		final	Connection connection = factory.newConnection();
		final	Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {			 
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
