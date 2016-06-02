package com.intern.alexx.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Client;
import com.intern.alexx.repository.ClientRepository;
import com.intern.alexx.services.ClientService;

@Component
public class ClientServiceImp implements ClientService {

	private static Logger log = LoggerFactory.getLogger(ClientServiceImp.class);

	@Autowired
	private ClientRepository clientRepo;

	@Transactional
	public Client getClientById(String clientId) {
		log.info("--Service-- Get client by id");
		return clientRepo.getClientById(clientId);
	}

	@Transactional
	public void insertClient(Client client) {
		log.info("--Service-- Insert client " + client.toString());
		clientRepo.insertClient(client);
	}

	@Transactional
	public void updateClient(Client client) {
		log.info("--Service-- Update client " + client.toString());
		clientRepo.updateClient(client);
	}

	@Transactional
	public void deleteClient(String clientId) {
		log.info("--Service-- Delete client ");
		clientRepo.deleteClient(clientId);
	}

}
