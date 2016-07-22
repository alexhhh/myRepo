package com.ikon.alexx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.ClientConverter; 
import com.ikon.alexx.model.ClientDTO;
import com.ikon.alexx.repository.ClientRepository;
import com.ikon.alexx.service.ClientService;

@Component
@Transactional
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientConverter clientConv ;


	@Override
	public ClientDTO getClientById(String clientId) { 
		return	clientConv.fromEntity(clientRepo.findOne(clientId));		 
	}

	@Override
	public void insertClient(ClientDTO client) {
		clientRepo.save(clientConv.toEntity(client));		
	}

	@Override
	public void updateClient(ClientDTO client) {
		clientRepo.save(clientConv.toEntity(client));
	}
	 
	@Override
	public void deleteClient(String clientId) {
		clientRepo.delete(clientId);
	}

}
