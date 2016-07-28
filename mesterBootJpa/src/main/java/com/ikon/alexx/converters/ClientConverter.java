package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Client;
import com.ikon.alexx.model.ClientDTO;

@Component
public class ClientConverter extends BaseConverter<ClientDTO, Client>{

	@Override
	public Client toEntity(ClientDTO pojo) {
		Client entity = new Client();
		entity.setId(pojo.getId());
		entity.setFirstName(pojo.getFirstName());
		entity.setLastName(pojo.getLastName()); 
		return entity;
	}

	@Override
	public ClientDTO fromEntity(Client entity) {
		ClientDTO pojo = new ClientDTO();
		pojo.setId(entity.getId());
		pojo.setFirstName(entity.getFirstName());
		pojo.setLastName(entity.getLastName());
		pojo.setUserId(entity.getUser().getId());
		return pojo;
	}

}
