package com.ikon.alexx.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.TokenConverter;
import com.ikon.alexx.entity.Token;
import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.repository.TokenRepository;
import com.ikon.alexx.service.TokenService;


@Component
@Transactional
public class TokenServiceImp implements  TokenService{

	@Autowired
	private TokenRepository tokenRepo;
	
	@Autowired
	private TokenConverter tokenConv;
	
	@Override
	public TokenDTO insert(String userName) {
		Token token = new Token();		
		token.setUserName(userName);
		java.sql.Date expDate = new java.sql.Date(Calendar.getInstance().getTime().getTime() + 864000000); // o zi			
		token.setExpirationDate(expDate); 		
		return	tokenConv.fromEntity(tokenRepo.save(token)); 
	}

	@Override
	public void delete(String id) {
		tokenRepo.delete(id);		
	}

	@Override
	public TokenDTO getById(String id) {
		return  tokenConv.fromEntity(tokenRepo.getOne(id));
	}

	@Override
	public TokenDTO getByUserName(String userName) {		 
		return  tokenConv.fromEntity(tokenRepo.findByUserName(userName));
	}

 
}
