package com.dorian.apirest.doriannewsbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	// 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Using your own implementation for authentication
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		// We can also use in-memory users: users created manually
	}



	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic() // Disabling the fake forms security: bcz it's an APIrest implementation
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // delete all sessions
			.and().addFilter(null);
	}
	*/
	

}
