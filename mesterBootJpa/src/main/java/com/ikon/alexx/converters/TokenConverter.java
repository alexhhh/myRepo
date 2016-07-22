package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Token;
import com.ikon.alexx.model.TokenDTO;

@Component
public class TokenConverter implements Converter<TokenDTO, Token> {

	@Override
	public Token toEntity(TokenDTO pojo) {
		Token entity = new Token();
		entity.setId(pojo.getId());
		entity.setUserName(pojo.getUserName());
		entity.setPassword(pojo.getPassword());
		entity.setExpirationDate(pojo.getExpirationDate());
		return entity;
	}

	@Override
	public TokenDTO fromEntity(Token entity) {
		TokenDTO pojo = new TokenDTO();
		pojo.setId(entity.getId());
		pojo.setUserName(entity.getUserName());
		pojo.setPassword(entity.getPassword());
		pojo.setExpirationDate(entity.getExpirationDate());
		return pojo;
	}

 
}
