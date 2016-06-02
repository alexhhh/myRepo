package com.intern.alexx.services;

import com.intern.alexx.model.Client;

public interface ClientService {

	Client getClientById(String clientId);

	void insertClient(Client client);

	void updateClient(Client client);

	void deleteClient(String clientId);

}
