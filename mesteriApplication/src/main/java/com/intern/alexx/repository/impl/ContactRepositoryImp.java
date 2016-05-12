package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Contact;

import com.intern.alexx.repository.ContactRepository;

@Component
public class ContactRepositoryImp implements ContactRepository {

	@Autowired
	private JdbcTemplate template;

	public void insert(Contact contact) {
		contact.setId(GUIDGenerator.generatedID());
		String sql = "INSERT INTO contact (ID, ID_MESTER, NUMAR_TELEFON, EMAIL, SITE, SOCIAL_PLATFORM) "
				+ "VALUES (?,?,?,?,?,?)";
		template.update(sql, new Object[] { contact.getId(), contact.getIdMester(), contact.getTelNr(),
				contact.getEmail(), contact.getSite(), contact.getSocialPlatform() });
	}

	public void update(Contact contact) {
		String sql = "UPDATE  contact SET NUMAR_TELEFON= ?, EMAIL= ?, SITE= ?  WHERE id_mester = ?";
		template.update(sql, new Object[] { contact.getTelNr(), contact.getEmail(), contact.getSite(),
				 contact.getIdMester() });
	}

	public void delete(String idMester) {
		String sql = "DELETE FROM contact  WHERE id_mester = ? ";
		template.update(sql, idMester);
	}

	public Contact getByIdMester(String idMester) {

		String sql = "SELECT * FROM contact WHERE contact.id_mester = ?";
		Contact contact = new Contact();
		template.query(sql, new Object[] {idMester}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getContactFromDB(contact, rs);
			}
		});

		return contact;
	}

	public String getIDByIdMester(String idMester) {
		String sql = "SELECT id FROM contact WHERE id_mester = ?";
		return	template.queryForObject(sql, new Object[] {idMester}, String.class);

	}
	
	
	private Contact getContactFromDB(Contact contact, ResultSet resultSet) throws SQLException {
		contact.setId(resultSet.getString("id"));
		contact.setIdMester(resultSet.getString("id_mester"));
		contact.setTelNr(resultSet.getString("numar_telefon"));
		contact.setEmail(resultSet.getString("email"));
		contact.setSite(resultSet.getString("site"));
		contact.setSocialPlatform(resultSet.getString("social_platform"));
		return contact;
	}

}
