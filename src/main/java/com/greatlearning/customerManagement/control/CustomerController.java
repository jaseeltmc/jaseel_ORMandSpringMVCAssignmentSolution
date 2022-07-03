package com.greatlearning.customerManagement.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customerManagement.model.Customer;
import com.greatlearning.customerManagement.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/list")
	public String getAllCustomer(Model theModel) {
		
		List<Customer> list = customerService.getAllCustomer();
		theModel.addAttribute("customerModel", list);
		return "customerlist";
	}
	
	@RequestMapping("/add")
	public String addCustomer(Model theModel) {
		
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "savecustomer";
	}
	
	@RequestMapping("/update")
	public String updateCustomer(@RequestParam("id") int id, Model theModel) {
		
		Customer customer = customerService.getCustomerById(id);
		theModel.addAttribute("customer", customer);
		return "savecustomer";
		
		
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("id") int id,Model theModel){
		
		Customer customer = customerService.delete(id);
		theModel.addAttribute("customer", customer);
		return "redirect:/customer/list";
		
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,@RequestParam ("firstName") String firstName,
			@RequestParam("lastName") String lastName,@RequestParam("email") String email) {
		
		Customer customer;
		if(id !=0) {
			customer = customerService.getCustomerById(id);
			customer.setCustomerId(id);;
			
		}else {
			customer = new Customer();
		}
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		
		customerService.saveCustomer(customer);
		
			
		
		return "redirect:/customer/list";
		
		
	}
	

}
