package com.intern.alexx.services;

import com.intern.alexx.model.Token;

public interface TokenService {

	
	public Token insert(String userName);
	
	public void delete(String id);
	
	public Token getById(String id);
	
	public Token getByUserName(String userName);
	
	
}
