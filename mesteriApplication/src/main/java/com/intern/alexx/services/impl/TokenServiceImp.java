package com.intern.alexx.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Token;
import com.intern.alexx.repository.TokenRepository;
import com.intern.alexx.services.TokenService;

@Component
public class TokenServiceImp implements TokenService {

	private static Logger log = LoggerFactory.getLogger(TokenServiceImp.class);
	
	@Autowired 
	private TokenRepository tokenRepo;
	
	@Transactional
	public Token insert(String userName) {
		log.info("--Service-- Insert token.");
		return tokenRepo.insert(userName);
	}

	@Transactional
	public void delete(String id) {
		log.info("--Service-- Delete token.");
		tokenRepo.delete(id);		
	}

	@Transactional
	public Token getById(String id) {
		log.info("--Service-- Get token by id.");
		return tokenRepo.getById(id);
	}

	@Transactional
	public Token getByUserName(String userName) {
		log.info("--Service-- Get token username.");
		return tokenRepo.getByUserName(userName);
	}

	
}
