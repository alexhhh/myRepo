package com.intern.alexx.repository;

import com.intern.alexx.model.Client;

public interface ClientRepository {

	public Client getClientById(String clientId) ;
	 
	public void insertClient(Client client);

	public void updateClient(Client client);

	public void deleteClient(String clientId);
	
}
