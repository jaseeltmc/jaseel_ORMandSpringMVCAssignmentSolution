package com.greatlearning.customerManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.customerManagement.model.Customer;




@Service
public class CustomerServiceImpl implements CustomerService {
	
	SessionFactory sessionFactory;
	Session session;
	
	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
		try {
			this.session = sessionFactory.getCurrentSession();
			}catch (HibernateException e) {
				this.session = sessionFactory.openSession();
			}
	}

	@Override
	@Transactional
	public List<Customer> getAllCustomer() {
		
		@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>)session.createQuery("from Customer").list();
		return list;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		session.saveOrUpdate(customer);
		
		
	}

	@Override
	@Transactional
	public Customer delete(int id) {
		
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();
		
		return customer;
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = session.get(Customer.class, id);
	
		return customer;
	}

}
