package com.dorian.apirest.doriannewsbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dorian.apirest.doriannewsbackend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByCustomerName(String costumerName);
	
	boolean existsByCustomerName(String customerName);

}
