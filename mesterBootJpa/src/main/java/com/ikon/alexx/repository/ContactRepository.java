package com.ikon.alexx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, String>{

	public Contact findByMesterId(String mesterId);
	
}
