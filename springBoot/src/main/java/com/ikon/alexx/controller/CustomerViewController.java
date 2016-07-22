package com.ikon.alexx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikon.alexx.repository.CustomerRepository;

@Controller
@RequestMapping(value = "/view")
public class CustomerViewController {

	@Autowired
	private CustomerRepository customerRepo;

	@RequestMapping(value = "/all")
	public String findAll(Model model) {
		model.addAttribute("customers", customerRepo.findAll());
		return "allcustomers";
	}

	@RequestMapping(value = "/byId")
	public String findById(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("customer", customerRepo.myFindCustomerByid(id));
		return "idcustomer";
	}
	
	@RequestMapping(value="/all_cust_review")
	public String allCustomerreviewById (@RequestParam(value="id") long id, Model model){
		
		return null;
	}

	// public void process(
	// HttpServletRequest request, HttpServletResponse response,
	// ServletContext servletContext, TemplateEngine templateEngine) {
	//
	//
	// WebContext ctx = new WebContext(request, response, servletContext,
	// request.getLocale());
	// ctx.setVariable("customers", customers);
	//
	// templateEngine.process("product/list", ctx, response.getWriter());
	// }

}
