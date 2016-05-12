package com.intern.alexx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Client;
import com.intern.alexx.repository.ClientRepository;
import com.intern.alexx.services.ClientService;

@Component
public class ClientServiceImp implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Transactional
	public Client getClientById(String clientId) {		 
		return clientRepo.getClientById(clientId);
	}

	@Override
	public void insertClient(Client client) {
		clientRepo.insertClient(client);
		
	}

	@Override
	public void updateClient(Client client) {
		clientRepo.updateClient(client);	
	}

	@Override
	public void deleteClient(String clientId) {
		clientRepo.deleteClient(clientId);
	}

}
