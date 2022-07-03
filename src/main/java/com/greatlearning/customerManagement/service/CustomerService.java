package com.greatlearning.customerManagement.service;

import java.util.List;

import com.greatlearning.customerManagement.model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomer();
	public void saveCustomer(Customer customer);
	public Customer delete(int id);
	public Customer getCustomerById(int id);

}
