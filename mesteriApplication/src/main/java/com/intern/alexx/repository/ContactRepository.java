/**
 * 
 */
package com.intern.alexx.repository;

import com.intern.alexx.model.Contact;

/**
 * @author malex
 *
 */
public interface ContactRepository {

	void insert(Contact contact);

	void update(Contact contact);

	void delete(String idContact);

	Contact getByIdMester(String idMester);

	String getIDByIdMester(String idMester);
}
