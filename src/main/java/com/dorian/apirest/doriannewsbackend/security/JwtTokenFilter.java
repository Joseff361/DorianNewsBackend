package com.dorian.apirest.doriannewsbackend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dorian.apirest.doriannewsbackend.service.CustomerServiceImpl;

// it will be executed in every request
public class JwtTokenFilter extends OncePerRequestFilter {
	
	
	// Using provider to check tokens
	@Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			
            String theToken = getTokenFromHeaderRequest(request);
            
            if(theToken != null && jwtProvider.validateToken(theToken)){
            	
            	// get the customerName from the token
                String customerName = jwtProvider.getCustomerNameFromToken(theToken);
                
                // get the userDetails by the name
                UserDetails userDetails = customerServiceImpl.loadUserByUsername(customerName);

                // Authentication process
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                // assign user to the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);
                
            }
            
        } catch (Exception e){
        	
            logger.error("doFilter method fails: " + e.getMessage());
            
        }
		
		// Pass the filter
        filterChain.doFilter(request, response);
			
	}
	
	
	// bcz the token is like: "Bearer ${token}..."
	private String getTokenFromHeaderRequest(HttpServletRequest request){
		
		String header = request.getHeader("Authorization");
        
		if(header != null && header.startsWith("Bearer")) {
			
			return header.replace("Bearer ", "");
			
		}
        
        return null;
		
	}

}
