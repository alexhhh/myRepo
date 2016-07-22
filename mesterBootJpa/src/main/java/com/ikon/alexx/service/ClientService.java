package com.ikon.alexx.service;

import com.ikon.alexx.model.ClientDTO;

public interface ClientService {

	ClientDTO getClientById(String clientId);

	void insertClient(ClientDTO client);

	void updateClient(ClientDTO client);

	void deleteClient(String clientId);

}
