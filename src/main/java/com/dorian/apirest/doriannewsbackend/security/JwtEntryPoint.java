package com.dorian.apirest.doriannewsbackend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;



// Check if a token exists; if not then 401 Not authorized
@Component	
public class JwtEntryPoint  implements AuthenticationEntryPoint {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		logger.error("Failed in commence function");
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
		
	}

}
