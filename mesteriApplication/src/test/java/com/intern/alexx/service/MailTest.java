package com.intern.alexx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.services.AuthMailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class MailTest {

	
	@Autowired
	private MailSender mailSender;
	
	
	
	@Test
	public void testMail (){
		
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setFrom("micu.alexandru.ioan@gmail.com");
		mail.setTo("robert.baban@gmail.com");
		mail.setSubject("test mail");
		mail.setText("nvm");
		mailSender.send(mail);
		
		
	}
}
