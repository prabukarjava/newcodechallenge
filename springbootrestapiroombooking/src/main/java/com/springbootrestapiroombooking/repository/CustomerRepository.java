package com.springbootrestapiroombooking.repository;

import com.springbootrestapiroombooking.model.Customer;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public class CustomerRepository extends JpaRepository<Customer,Long> {


	
}
