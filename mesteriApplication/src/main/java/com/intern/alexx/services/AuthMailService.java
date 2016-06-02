package com.intern.alexx.services;

import org.springframework.stereotype.Component;

import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;

@Component
public interface AuthMailService {

	void AuthMailContent(User user, Token token);

	void ResetPasswordMail(User user, Token token);
}
