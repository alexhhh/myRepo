package com.intern.alexx.services;

import com.intern.alexx.model.Client;

public interface ClientService {

	public Client getClientById(String clientId) ;
	 
	public void insertClient(Client client);

	public void updateClient(Client client);

	public void deleteClient(String clientId);
	
}
