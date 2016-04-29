package com.intern.alexx.services.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage; 

import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;
import com.intern.alexx.services.AuthMailService;


public class AuthMailServiceImp implements AuthMailService {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthMailServiceImp.class);
	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public void AuthMailContent(User user, Token token) {
		LOGGER.info("---------------------mda------------------------");
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		LOGGER.info("-----"+ user.toString()+"-------"+token.toString()+"------");
		msg.setTo(user.getEmail());
		msg.setText("Dear user " + user.getUserName()
				+ "  please CLICK THE LINK BELOW to confirm your email and to finish the authentication. You have 7 days for confirmation.  "
				+ "http://localhost/#/activateUser/" + token.getId() + ";");
		LOGGER.info(msg.getText());
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

}