package com.intern.alexx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Token;
import com.intern.alexx.repository.TokenRepository;
import com.intern.alexx.services.TokenService;

@Component
public class TokenServiceImp implements TokenService {

	@Autowired 
	private TokenRepository tokenRepo;
	
	@Transactional
	public Token insert(String userName) {		 
		return tokenRepo.insert(userName);
	}

	@Transactional
	public void delete(String id) {
		tokenRepo.delete(id);		
	}

	@Transactional
	public Token getById(String id) {		 
		return tokenRepo.getById(id);
	}

	@Transactional
	public Token getByUserName(String userName) {
		return tokenRepo.getByUserName(userName);
	}

	
}
