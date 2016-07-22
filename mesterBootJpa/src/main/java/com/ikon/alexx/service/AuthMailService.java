package com.ikon.alexx.service;

import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;

public interface AuthMailService {

	void AuthMailContent(UserDTO user, TokenDTO token);

	void ResetPasswordMail(UserDTO user, TokenDTO token);
}
