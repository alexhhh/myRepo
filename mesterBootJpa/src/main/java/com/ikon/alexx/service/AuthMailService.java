package com.ikon.alexx.service;

import javax.mail.MessagingException;

import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;

public interface AuthMailService {

	void authMailContent(UserDTO user, TokenDTO token) throws MessagingException;

	void resetPasswordMail(UserDTO user, TokenDTO token) throws MessagingException;
}
