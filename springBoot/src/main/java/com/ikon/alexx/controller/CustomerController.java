package com.ikon.alexx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ikon.alexx.model.Customer;
import com.ikon.alexx.repository.CustomerRepository;

@Controller
@RequestMapping(value = "/json")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;

	@RequestMapping(value = "/all")
	@ResponseBody
	public Iterable<Customer> findAll() {
		return customerRepo.findAll();
	}
	
 
	@RequestMapping(value = "/bylastname")
	@ResponseBody
	public Iterable<Customer> findByLastName(String lastName) {
		return customerRepo.findByLastName(lastName);
	}

	@RequestMapping(value = "/custom")
	@ResponseBody
	public Iterable<Customer> findByCustomLastName(String lastName) {
		return customerRepo.myFindByLastName(lastName);
	}

	@RequestMapping(value = "/firstname")
	@ResponseBody
	public Iterable<Customer> findByCustomFistName(String firstName) {
		return customerRepo.myFindByFirstName(firstName);
	}

	@RequestMapping(value = "/byid")
	@ResponseBody
	public Customer findCustomerByid(Long id) {
		return customerRepo.myFindCustomerByid(id);
	}

}
