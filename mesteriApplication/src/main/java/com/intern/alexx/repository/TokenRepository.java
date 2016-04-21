package com.intern.alexx.repository;

import com.intern.alexx.model.Token;

public interface TokenRepository {

	Token insert(String userName);

	void delete(String id);

	public Token getById(String id);

	public Token getByUserName(String userName);
}
