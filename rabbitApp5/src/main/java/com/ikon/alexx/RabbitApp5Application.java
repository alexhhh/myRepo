package com.ikon.alexx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitApp5Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(RabbitApp5Application.class, args);

		(new Thread(new ConsumerApp5())).start();
		Thread.sleep(1000);
		
		(new Thread(new ProducerApp5())).start();
	}

}
