package com.ikon.alexx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikon.alexx.model.Customer;
import com.ikon.alexx.model.Review;
import com.ikon.alexx.model.Speciality;
import com.ikon.alexx.repository.CustomerRepository;
import com.ikon.alexx.repository.ReviewRepository;
import com.ikon.alexx.repository.SpecialityRepository;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private SpecialityRepository specRepo;

	@Transactional
	@RequestMapping(value = "/addReview")
	public String addReview(@RequestParam("id") long id, Model model) {

		Customer c1 = customerRepo.findOne(id);
		Review review = new Review();
		review.setTitle("rev 1");
		review.setPrice(3);
		review.setRating(3);
		review.setFeedback("ceva acolo");
		review.setCustomer(c1);		
		model.addAttribute("review", review);
		reviewRepository.save(review);
		return "customerreview" ;
	}
	
	
	@Transactional
	@RequestMapping(value = "/addCustomer&Review")
	public String addCutomerAndReview(Model model){
		
		Customer c= new Customer();
		c.setFirstName("Ionica");
		c.setLastName("Chipciou");
		Review review = new Review();
		review.setTitle("rev 1");
		review.setPrice(3);
		review.setRating(3);
		review.setFeedback("ceva acolo");
		review.setCustomer(c);	
		List<Review> reviews = new ArrayList<>() ;
		reviews.add(review);
		c.setReviews(reviews);
		customerRepo.save(c);
		//reviewRepository.save(review);
		model.addAttribute("customer", c);
		model.addAttribute("review", review);
		
		return "customerreview";
	}
	
	
	@Transactional
	@RequestMapping(value="/m2m")
	public String m2m (Model model){
		
		Speciality s1 = new Speciality();
		Speciality s2 = new Speciality();
		s1.setSpecialityName("electrician");
		s2.setSpecialityName("zugrav");
		
		Customer c = new Customer();
		c.setFirstName("Badea");
		c.setLastName("Ion");
		
		Set<Speciality> specialities = new HashSet<>();
		Set<Customer> customers = new HashSet<>();
		specialities.add(s1);
		specialities.add(s2);
		customers.add(c);
		
		c.setSpecialities(specialities);
		s1.setCustomers(customers);
		s2.setCustomers(customers);
		
		
		//customerRepo.save(c);
		specRepo.save(s1);
		
		Map<String, Object> response = new HashMap<>() ;
		response.put("customer", c);
		response.put("specialty", s1);
		response.put("speciality", s2); 
		
		model.addAttribute("response", response);
		return "general";
	}

	@Transactional
	@RequestMapping(value="/m2m2")
	public String m2m2 (@RequestParam("firstName") String firstName,     Model model){
		
		List<Customer> customers = customerRepo.findByFirstName(firstName);
		
		Speciality s = new Speciality();
		s.setSpecialityName("smecher");
		
		Set<Speciality> spec = new HashSet<>();
		Set<Customer> cust = new HashSet<>();
		
		
		for(Customer c :customers){
			cust.add(c);
			spec.add(s);
			s.setCustomers(cust);
			c.setSpecialities(spec);
		}
		
		specRepo.save(s);
		model.addAttribute("customers", customers);
		return "general";
	}
	
}
