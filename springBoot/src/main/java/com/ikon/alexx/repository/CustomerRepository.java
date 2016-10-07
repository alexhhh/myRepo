package com.ikon.alexx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikon.alexx.model.Customer; 
 
public interface CustomerRepository extends JpaRepository<Customer, Long>, MyCustomerRepository  {

	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstName(String firstName); 

}