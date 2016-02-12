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

	void delete(Contact contact);

	Contact getByIdMester(Contact contact);
}
