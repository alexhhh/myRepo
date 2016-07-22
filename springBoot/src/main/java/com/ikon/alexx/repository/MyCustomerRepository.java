package com.ikon.alexx.repository;

import java.util.List;

import com.ikon.alexx.model.Customer;

public interface MyCustomerRepository {

	List<Customer> myFindByLastName(String lastName);
	
	List<Customer> myFindByFirstName(String firstName);
	
	Customer myFindCustomerByid (long id);

}
