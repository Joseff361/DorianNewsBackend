package com.dorian.apirest.doriannewsbackend.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorian.apirest.doriannewsbackend.entity.Message;
import com.dorian.apirest.doriannewsbackend.entity.News;
import com.dorian.apirest.doriannewsbackend.security.JwtProvider;
import com.dorian.apirest.doriannewsbackend.service.CustomerServiceImpl;
import com.dorian.apirest.doriannewsbackend.service.NewsService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api")
@PreAuthorize("authenticated") // Allow authenticaded users no matter what type of rule it has
public class NewsRestController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
    private JwtProvider jwtProvider;

	@GetMapping("/news")
	public List<News> findAll(HttpServletRequest request){
		
		String theToken = getTokenFromHeaderRequest(request);
		
		String theCustomerName = jwtProvider.getCustomerNameFromToken(theToken);
		
		List<News> newss = newsService.findByCustomer(customerServiceImpl.findByCustomerName(theCustomerName));
		
		return newss;
	}
	
	/*
	@GetMapping("/news/{theId}")
	public News getNewsById(@PathVariable int theId) {
		
		News news = newsService.findById(theId);
		
		return news;
		
	}
	*/
	
	@PostMapping("/news")
	public ResponseEntity<?> addNews(@RequestBody News theNews, HttpServletRequest request) {
		
		String theToken = getTokenFromHeaderRequest(request);
		
		String theCustomerName = jwtProvider.getCustomerNameFromToken(theToken);
		
		theNews.setId(0);
		
		theNews.setCustomer(customerServiceImpl.findByCustomerName(theCustomerName));
		
		newsService.save(theNews);
		
		return new ResponseEntity<>(theNews, HttpStatus.OK);
	}
	
	/*
	@PutMapping("/news")
	public News updateNews(@RequestBody News theNews) {
		
		newsService.save(theNews);
		
		return theNews;
		
	}
	*/
	
	@DeleteMapping("/news/{theId}")
	public ResponseEntity<?> deleteNews(@PathVariable int theId, HttpServletRequest request) {
		
		
		String theToken = getTokenFromHeaderRequest(request);
		
		String theCustomerName = jwtProvider.getCustomerNameFromToken(theToken);
		
		News news = newsService.findByCustomerAndId(customerServiceImpl.findByCustomerName(theCustomerName), theId);
		
		if(news == null) {
			throw new RuntimeException("News id not found - " + theId);
		}
		
		newsService.deleteById(theId);
		
		return new ResponseEntity<>(new Message("Deleted new id - " + theId), HttpStatus.OK);
	}
	
	
	private String getTokenFromHeaderRequest(HttpServletRequest request){
		
		String header = request.getHeader("Authorization");
        
		if(header != null && header.startsWith("Bearer")) {
			
			return header.replace("Bearer ", "");
			
		}
        
        return null;
		
	}
	
	
}
