package com.ikon.alexx;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.*;

@SpringBootApplication
public class RabbitApp5Application {

	static int msgCount = 100 ;
    final static String QUEUE_NAME = "confirm-test";
    static ConnectionFactory connectionFactory;	
 
	public static void main(String[] args) {
		SpringApplication.run(RabbitApp5Application.class, args);
		
		if (args.length > 0) {
            msgCount = Integer.parseInt(args[0]);
    }

    connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost"); 
    
    // Consume msgCount messages.
    (new Thread(new ConsumerApp5())).start();
    // Publish msgCount messages and wait for confirms.
    (new Thread(new ProducerApp5())).start();
	}
	
	static class ProducerApp5 implements Runnable {
        public void run() { 
            try {
                long startTime = System.currentTimeMillis();

                // Setup
                Connection conn = connectionFactory.newConnection();
                Channel ch = conn.createChannel();
                ch.queueDeclare(QUEUE_NAME, true, false, false, null);
                ch.confirmSelect(); //Enables publisher acknowledgements on this channel.

                // Publish
                for (long i = 0; i < msgCount; ++i) {                 
                    String message = "I was wondering if after all these years";
            		ch.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, message.getBytes());		
            		System.out.println(" ["+i+"] Sent '" + message + "'");
                }

                ch.waitForConfirmsOrDie();

                // Cleanup
                //ch.queueDelete(QUEUE_NAME);
                ch.close();
                conn.close();

                long endTime = System.currentTimeMillis();
                System.out.printf("Test took %.3fs\n",
                                  (float)(endTime - startTime)/1000);
            } catch (Throwable e) {
                System.out.println("foobar :(");
                System.out.print(e);
            }
        } }
	
	static class ConsumerApp5 implements Runnable {
        public void run() {
            try {
                // Setup
                Connection conn = connectionFactory.newConnection();
                Channel ch = conn.createChannel();
                ch.queueDeclare(QUEUE_NAME, true, false, false, null);

                // Consume
//                QueueingConsumer qc = new QueueingConsumer(ch);
//                ch.basicConsume(QUEUE_NAME, true, qc);
//                for (int i = 0; i < msgCount; ++i) {
//                    qc.nextDelivery();
//                }
                //display
                
                Consumer consumer = new DefaultConsumer(ch) {                	 
        			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
        					byte[] body) throws IOException {
        				String message = new String(body, "UTF-8");
        				System.out.println(" [x] Received '" + message + "'");
        			}
        		};
        		 
        		ch.basicConsume(QUEUE_NAME, true, consumer);
               
                // Cleanup
                 
            } catch (Throwable e) {
                System.out.println("Whoosh!");
                System.out.print(e);
            }
        }}
	
	
	
}
