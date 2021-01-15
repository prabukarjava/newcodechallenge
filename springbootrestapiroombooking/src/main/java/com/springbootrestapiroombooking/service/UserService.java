package com.springbootrestapiroombooking.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springbootrestapiroombooking.dao.CustomerDataAo;
import com.springbootrestapiroombooking.model.Customer;

public interface UserService extends UserDetailsService {


	Customer save(Customer customer);

	

}