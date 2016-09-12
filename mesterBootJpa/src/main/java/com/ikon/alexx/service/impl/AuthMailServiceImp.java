package com.ikon.alexx.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.service.AuthMailService;

@Component
public class AuthMailServiceImp implements AuthMailService {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthMailServiceImp.class);

	@Autowired
	private JavaMailSender mailSender;

	public void authMailContent(UserDTO user, TokenDTO token) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msg = new MimeMessageHelper(message, true);
		msg.setSubject("Authentication");
		msg.setTo(user.getEmail());
		msg.setText("Dear user " + user.getUserName()
				+ "  please CLICK THE LINK BELOW to confirm your email and to finish the authentication. You have 10 days for confirmation.  "
				+ "http://localhost/#/activateUser/" + token.getId() + ";", true);
		LOGGER.info("--Authentication Mail--" + msg.toString());
		mailSender.send(message);
	}

	public void resetPasswordMail(UserDTO user, TokenDTO token) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msg= new MimeMessageHelper(message, true);
		msg.setSubject("Reset password");
		msg.setTo(user.getEmail());
		msg.setText("Dear user " + user.getUserName()
				+ "  If you want to reset your password please CLICK THE LINK BELOW to confirm and finish this request. "
				+ "http://localhost/#/resetPassword/" + token.getId() + ";", true);
		LOGGER.info("--Reset password mail" + msg.toString());
		mailSender.send(message);
	}
 
}