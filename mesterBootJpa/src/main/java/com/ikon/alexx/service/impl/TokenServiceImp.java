package com.ikon.alexx.service.impl;

import java.sql.Date;
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
public class TokenServiceImp implements TokenService {

	@Autowired
	private TokenRepository tokenRepo;

	@Autowired
	private TokenConverter tokenConv;

	private long currentDate = Calendar.getInstance().getTime().getTime();

	@Override
	public TokenDTO insert(String userName) {
		return tokenConv.fromEntity(tokenRepo.save(setAllForToken(userName)));
	}

	@Override
	public void delete(String id) {
		tokenRepo.delete(id);
	}

	@Override
	public TokenDTO getById(String id) {
		return tokenConv.fromEntity(tokenRepo.getOne(id));
	}

	@Override
	public TokenDTO getByUserName(String userName) {
		return tokenConv.fromEntity(tokenRepo.findByUserName(userName));
	}

	private Token setAllForToken(String userName) {
		Token token = new Token();
		token.setUserName(userName);
		token.setExpirationDate(new Date(currentDate + 864000000));
		return token;
	}
}
