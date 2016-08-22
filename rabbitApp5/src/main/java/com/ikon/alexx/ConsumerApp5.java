package com.ikon.alexx;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConsumerApp5 implements Runnable {

	private static final String QUEUE_NAME = "confirm-test";
	private static final String ERR_QUEUE_NAME = "errorQueue";
	private static final String EXCHANGE_NAME = "exchange-test";
	private static final String ERR_EXCHANGE_NAME = "errorEx";
	private static final String ROUTING_KEY = "key";

	public void run() {
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			Connection conn = connectionFactory.newConnection();
			Channel ch = conn.createChannel();

			ch.exchangeDeclare(EXCHANGE_NAME, "direct");
			ch.exchangeDeclare(ERR_EXCHANGE_NAME, "direct", true);
			
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-dead-letter-exchange", ERR_EXCHANGE_NAME);
			args.put("x-dead-letter-routing-key", ROUTING_KEY);
			ch.queueDeclare(QUEUE_NAME, true, false, false, args);
			ch.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

			ch.queueDeclare(ERR_QUEUE_NAME, true, false, false, null);
			ch.queueBind(ERR_QUEUE_NAME, ERR_EXCHANGE_NAME, ROUTING_KEY);
			
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			RabbitConsumer consumer = new RabbitConsumer(ch, new DefaultMessageHandler());

			ch.basicConsume(QUEUE_NAME, false, consumer);			
		} catch (Throwable e) {
			e.printStackTrace();

		}
	}
}