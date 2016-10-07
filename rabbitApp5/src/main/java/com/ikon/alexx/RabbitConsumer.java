package com.ikon.alexx;

import java.io.IOException;

import com.ikon.alexx.exceptions.RecoverableException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitConsumer extends DefaultConsumer {

	private MessageHandler messageHandler ;
	
	public RabbitConsumer(Channel channel, MessageHandler messageHandler) {
		super(channel);
		this.messageHandler=messageHandler;
	}

	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {
		 Boolean reQueue = false;
		try {
			messageHandler.handleMessage(properties.getHeaders(), body);
			getChannel().basicAck(envelope.getDeliveryTag(), false);
		} catch (RecoverableException e) {		 
			e.printStackTrace();			
			reQueue= ! envelope.isRedeliver();
			getChannel().basicNack(envelope.getDeliveryTag(), false, reQueue);
		} catch (Throwable tr){
			tr.printStackTrace();
			reQueue=false;
			getChannel().basicNack(envelope.getDeliveryTag(), false, reQueue);
		}
 
	}

}
