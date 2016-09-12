package com.ikon.alexx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.ClientConverter;
import com.ikon.alexx.entity.Client;
import com.ikon.alexx.model.ClientDTO;
import com.ikon.alexx.repository.ClientRepository;
import com.ikon.alexx.repository.UserRepository;
import com.ikon.alexx.service.ClientService;

@Component
@Transactional
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private ClientConverter clientConv;

	@Autowired
	private UserRepository userRepo;

	@Override
	public ClientDTO getClientById(String clientId) {
		return clientConv.fromEntity(clientRepo.findOne(clientId));
	}

	@Override
	public void insertClient(ClientDTO clientDTO) { 
		clientRepo.save(setUserForTheClient(clientDTO));
	}

	@Override
	public void updateClient(ClientDTO clientDTO) {
		clientRepo.save(setUserForTheClient(clientDTO));
	}

	@Override
	public void deleteClient(String clientId) {
		clientRepo.delete(clientId);
	}

	@Override
	public ClientDTO getClientByUserId(String userId) {
		return clientConv.fromEntity(clientRepo.findByUserId(userId));
	}

	private Client setUserForTheClient(ClientDTO clientDTO){
		Client client = clientConv.toEntity(clientDTO);
		client.setUser(userRepo.findOne(clientDTO.getUserId()));
		return client;
	}
	
}
