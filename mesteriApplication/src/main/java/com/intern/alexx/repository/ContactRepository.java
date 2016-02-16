/**
 * 
 */
package com.intern.alexx.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.intern.alexx.model.Contact;

/**
 * @author malex
 *
 */
public interface ContactRepository {
	void insert(Contact contact);

	void update(Contact contact);

	void delete(Contact contact);

	Contact getByIdMester(Contact contact);

	public void transactionalInsertContract(Integer mesterKey, Contact contact, Connection conn, PreparedStatement ps)
			throws SQLException;

	public void transactionalUpdateContract(Integer mesterKey, Contact contact, Connection conn, PreparedStatement ps)
			throws SQLException;

	public void transactionalDeleteContract(Integer mesterKey, Connection conn, PreparedStatement ps)
			throws SQLException;
}
