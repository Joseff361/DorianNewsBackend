package com.dorian.apirest.doriannewsbackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dorian.apirest.doriannewsbackend.dao.CustomerRepository;
import com.dorian.apirest.doriannewsbackend.entity.Customer;

@Service
public class CustomerServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String theCostumername) throws UsernameNotFoundException {
		
		Customer theCustomer = customerRepository.findByCustomerName(theCostumername);
		
		// This is a user from spring security
		if(theCustomer == null) {
			
			throw new UsernameNotFoundException("User " + theCostumername + " not found");
			
		}else {
			
			// The rules can be: USER, MANAGER, ADMIN, etc
			// the password should be encoded
			return userBuilder(theCustomer.getCustomerName(), theCustomer.getPassword(),  new ArrayList<String>(Arrays.asList("USER")));
		
		}
	}
	
	private User userBuilder(String theUsername, String thePassword, List<String> rules) {
	
		// Default parameters
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
				
		// Looping trough rules
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(String rule: rules) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + rule));
		}
		
		return new User(theUsername, thePassword, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
	}
	
	
	public boolean existsByCustomerName(String customerName) {
		
		return customerRepository.existsByCustomerName(customerName);
		
	}
	
	public void save(Customer theCustomer) {
		
		customerRepository.save(theCustomer);
	}
}
