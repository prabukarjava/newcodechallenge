package com.springbootrestapiroombooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrestapiroombooking.model.Customer;
import com.springbootrestapiroombooking.dao.CustomerDAO;


@RestController
@RequestMapping("/company")
public class CustomerController {
	
	@Autowired
	CustomerDAO customerDAO;

	/*to save an customer*/
	@PostMapping("/customer")
	public Customer createCustomer(@Valid @RequestBody Customer cus) {
		return customerDAO.save(cus);
	}
	
	/*get all employees*/
	@GetMapping("/customer")
	public List<Customer> getAllCustomer(){
		return customerDAO.findAll();
	}
	
	/*get customer by id*/
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable(value="id") Long id){
		
		Customer cus=customerDAO.findOne(id);
		
		if(cus==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cus);
		
	}
	
	/*update an customer by id*/
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id") Long id,@Valid @RequestBody Customer cusDetails){
		
		Customer cus=customerDAO.findOne(id);
		if(cus==null) {
			return ResponseEntity.notFound().build();
		}
		
		cus.setfirstname(cusDetails.getfirstname());
		cus.setLastname(cusDetails.getLastname());
		cus.setDob(cusDetails.getDob());
		cus.setEmail(cusDetails.getEmail());
		cus.setPassword(cusDetails.getPassword());
		
		Customer updateCustomer=customerDAO.save(cus);
		return ResponseEntity.ok().body(updateCustomer);
		
		
		
	}
	
	/*Delete an employee*/
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value="id") Long id){
		
		Customer cus=customerDAO.findOne(id);
		if(cus==null) {
			return ResponseEntity.notFound().build();
		}
		customerDAO.delete(cus);
		
		return ResponseEntity.ok().build();
		
		
	}
	
}
