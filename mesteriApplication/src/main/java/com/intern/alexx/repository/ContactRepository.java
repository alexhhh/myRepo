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
	
	void insert(Contact contact, String mesterId);

	void update(Contact contact, String mesterId);

	void delete(String idContact);

	Contact getByIdMester(String idMester);

}
