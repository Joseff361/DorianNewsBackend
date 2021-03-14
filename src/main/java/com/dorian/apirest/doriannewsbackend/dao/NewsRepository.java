package com.dorian.apirest.doriannewsbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dorian.apirest.doriannewsbackend.entity.Customer;
import com.dorian.apirest.doriannewsbackend.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

	public List<News> findByCustomer(Customer theCustomer);
	
	public News findByCustomerAndId(Customer theCustomer, int id);
	
}
