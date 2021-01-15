package com.springbootrestapiroombooking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;
import com.springbootrestapiroombooking.model.Customer;
import com.springbootrestapiroombooking.repository.CustomerRepository;



@Service
public class CustomerDAO {

	@Autowired
	CustomerRepository customerRepository;
	
	/* to save*/
	
	public Customer save(Customer cus) {
		return customerRepository.save(cus);
	}
	  
	
	
	/*search*/
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	
	/*get an customer*/
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}
	
	/*delete an customer*/
		
		public void delete(Customer cus) {
			customerRepository.delete(cus);
		}
}
