package com.dorian.apirest.doriannewsbackend.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorian.apirest.doriannewsbackend.entity.Customer;
import com.dorian.apirest.doriannewsbackend.entity.JwtDto;
import com.dorian.apirest.doriannewsbackend.entity.LoginCustomer;
import com.dorian.apirest.doriannewsbackend.entity.Message;
import com.dorian.apirest.doriannewsbackend.security.JwtProvider;
import com.dorian.apirest.doriannewsbackend.service.CustomerServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
public class CustomerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private JwtProvider jwtProvider;
    
    
    @PostMapping("/signin")
    // return a generic entity = js object = any[]
    public ResponseEntity<?> signIn(@RequestBody Customer theCustomer, BindingResult bindingResult){
    	
        if(bindingResult.hasErrors()) {
        	
            return new ResponseEntity<>(new Message("Malformed form"), HttpStatus.BAD_REQUEST);
        	
        }
        
        if(customerServiceImpl.existsByCustomerName(theCustomer.getCustomerName())){
        	
        	return new ResponseEntity<>(new Message("Customer Name already exists"), HttpStatus.BAD_REQUEST);
        	
        }
            
        // Creating the new encoded customer 
        Customer customerToSave =
                new Customer(theCustomer.getCustomerName(), passwordEncoder.encode(theCustomer.getPassword()),
                		theCustomer.getName(), theCustomer.getLastName());
        
        
        customerServiceImpl.save(customerToSave);
        
        return new ResponseEntity<>(new Message("Customer saved to BBDD"), HttpStatus.OK);
        
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginCustomer loginCustomer, BindingResult bindingResult){
       
    	if(bindingResult.hasErrors()) {
    		
            return new ResponseEntity(new Message("Malformed form"), HttpStatus.BAD_REQUEST);
    		
    	}
    	
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCustomer.getCustomerName(), loginCustomer.getPassword()));
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String stringToken = jwtProvider.generateToken(authentication);
        
        UserDetails theCustomerDetails= (UserDetails) authentication.getPrincipal();
        
        // authorities by default
        List<GrantedAuthority> authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
                
        JwtDto jwtDto = new JwtDto(stringToken, theCustomerDetails.getUsername(), authorities);
        
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
    
    
    
}
