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
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(user.getEmail());
		msg.setSubject("Authentication");
		msg.setText("Dear user " + user.getUserName()
				+ "  please CLICK THE LINK BELOW to confirm your email and to finish the authentication. You have 10 days for confirmation.  "
				+ "http://localhost/#/activateUser/" + token.getId() + ";");
		LOGGER.info("--Authentication Mail--" + msg.toString());
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	public void ResetPasswordMail(User user, Token token) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(user.getEmail());
		msg.setSubject("Reset password");
		msg.setText("Dear user " + user.getUserName()
				+ "  If you want to reset your password please CLICK THE LINK BELOW to confirm and finish this request. "
				+ "http://localhost/#/resetPassword/" + token.getId() + ";");
		LOGGER.info("--Reset password mail" + msg.toString());
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}
}