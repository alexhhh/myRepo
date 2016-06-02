package com.intern.alexx.repository;

import com.intern.alexx.model.Token;

public interface TokenRepository {

	Token insert(String userName);

	void delete(String id);

	Token getById(String id);

	Token getByUserName(String userName);
}
