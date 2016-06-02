package com.intern.alexx.services;

import com.intern.alexx.model.Token;

public interface TokenService {

	Token insert(String userName);

	void delete(String id);

	Token getById(String id);

	Token getByUserName(String userName);

}
