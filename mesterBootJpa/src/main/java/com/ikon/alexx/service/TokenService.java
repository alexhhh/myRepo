package com.ikon.alexx.service;

import com.ikon.alexx.model.TokenDTO;

public interface TokenService {

	TokenDTO insert(String userName);

	void delete(String id);

	TokenDTO getById(String id);

	TokenDTO getByUserName(String userName);

}
