package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Client;
import com.intern.alexx.repository.ClientRepository; 

@Component
public class ClientRepositoryImp implements ClientRepository{

	private static Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);

	@Autowired
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}
  
	public Client getClientById(String clientId) {
		Client client = new Client();
		String sql = "SELECT * FROM client WHERE id = ? ";
		template.query(sql, new Object[] { clientId }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getClientFromDB(client, rs);
				LOGGER.info("---get client-" + client.toString());
			}
		});
		return client;		 
	}
 
	@Override
	public void insertClient(Client client) {
		String sql = "INSERT INTO client (ID, FIRST_NAME, LAST_NAME, USER_ID) " + "VALUES (?,?,?,?)";
		template.update(sql, new Object[] { client.getId(), client.getFirstName(), client.getLastName(),  client.getClientUserId() });
		LOGGER.info("---insert client---" + client.toString());		
	}

	@Override
	public void updateClient(Client client) {
		String sql = "UPDATE  client SET FIRST_NAME=?, LAST_NAME=? where ID=? " ;
		template.update(sql, new Object[] {client.getFirstName(), client.getLastName(), client.getId()});
		LOGGER.info("---edit client---" + client.toString());
	}

	@Override
	public void deleteClient(String clientId) {
		String sql = "DELETE FROM client  WHERE id = ? ";
		template.update(sql, clientId);		
	}

	private Client getClientFromDB(Client client, ResultSet resultSet) throws SQLException {
	 		client.setId(resultSet.getString("id"));
			client.setFirstName(resultSet.getString("first_name"));
			client.setLastName(resultSet.getString("last_name"));
			client.setClientUserId(resultSet.getString("user_id"));
			return client;
		
	}
}
