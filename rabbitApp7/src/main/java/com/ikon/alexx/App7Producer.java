package com.ikon.alexx;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class App7Producer {

	private static final String EXCHANGE_TOPIC_NAME = "news";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_TOPIC_NAME, "topic");
		String severity = getSeverity(argv);
		String message = getMessage(argv);

		channel.basicPublish(EXCHANGE_TOPIC_NAME, severity, null, message.getBytes());

		System.out.println(" [x] Sent '" + severity + "': '" + message + "'");

		channel.close();
		connection.close();
	}

	private static String getSeverity(String[] strings) {
		// routingKey pattern about.location.source.type
		if (strings.length < 1)
			return "life.regional.bbc.nomal";
		return strings[0];
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 2)
			return "Nimic nou pe frontul de vest";
		return joinStrings(strings, " ", 1);
	}

	private static String joinStrings(String[] strings, String delimiter, int startIndex) {
		int length = strings.length;
		if (length == 0)
			return "";
		if (length < startIndex)
			return "";
		StringBuilder words = new StringBuilder(strings[startIndex]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
