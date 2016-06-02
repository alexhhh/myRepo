package com.intern.alexx.repository;

import com.intern.alexx.model.Client;

public interface ClientRepository {

	Client getClientById(String clientId);

	void insertClient(Client client);

	void updateClient(Client client);

	void deleteClient(String clientId);

}
