package com.dorian.apirest.doriannewsbackend.service;

import java.util.List;

import com.dorian.apirest.doriannewsbackend.entity.Customer;
import com.dorian.apirest.doriannewsbackend.entity.News;

public interface NewsService {
	
	public List<News> findAll();
	
	public News findById(int id);
	
	public void save(News news);
	
	public void deleteById(int id);
	
	public List<News> findByCustomer(Customer theCustomer);
	
	public News findByCustomerAndId(Customer theCustomer, int theId);

}
