package com.springbootrestapiroombooking.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootrestapiroombooking.dao.CustomerDataAo;
import com.springbootrestapiroombooking.model.Customer;
import com.springbootrestapiroombooking.model.Role;
import com.springbootrestapiroombooking.repository.CustomerRepository;





@Service
public class UserServiceImpl implements UserService {
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer save(Customer customer) {
		CustomerDataAo customerDataAo;
		Customer customer = new Customer(customerDataAo.getFirstname(),
				customerDataAo.getLastname(), customerDataAo.getEmail(),customerDataAo.getDob(),
				passwordEncoder.encode(customerDataAo.getPassword()));
		
		return customerRepository.save(customer);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Customer customer = customerRepository.findByEmail(username);
		if(customer == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.Customer(customer.getEmail(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return (Collection<? extends GrantedAuthority>) roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	
	
}
