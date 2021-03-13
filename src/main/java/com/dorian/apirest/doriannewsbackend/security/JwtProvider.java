package com.dorian.apirest.doriannewsbackend.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dorian.apirest.doriannewsbackend.entity.Customer;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


// Create tokens and valid them
@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication){
    
		// authentication process and then converted
		UserDetails theCustomerDetails = (UserDetails) authentication.getPrincipal();
    
		// returning the token
		return Jwts.builder().setSubject(theCustomerDetails.getUsername()) // the subject will be the customerName
					.setIssuedAt(new Date()) // emitted at
					.setExpiration(new Date(new Date().getTime() + expiration * 1000))
					.signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	 }
	
	 public String getCustomerNameFromToken(String token){
	 
		// the subject will be the customerName
		 return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	 }
	 
	 
	 public boolean validateToken(String token){
        
		try { 
            
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            
			return true; // Token validated
			
        }catch (MalformedJwtException e){
            
        	logger.error("Token mal formed");
            
        }catch (UnsupportedJwtException e){
        	
            logger.error("Unsupported token");
            
        }catch (ExpiredJwtException e){
            
        	logger.error("Expired token");
        
        }catch (IllegalArgumentException e){
        	
            logger.error("Null token");
            
        }catch (SignatureException e){
            
        	logger.error("Signature fails");
        
        }
		
        return false;
        
	 }
	 


}
