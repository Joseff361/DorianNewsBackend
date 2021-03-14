package com.dorian.apirest.doriannewsbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorian.apirest.doriannewsbackend.dao.NewsRepository;
import com.dorian.apirest.doriannewsbackend.entity.Customer;
import com.dorian.apirest.doriannewsbackend.entity.News;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public List<News> findAll() {
		
		List<News> newss = newsRepository.findAll();
		
		return newss;
	}

	@Override
	public News findById(int id) {
	
		Optional<News> news = newsRepository.findById(id); 
		
		if(news.isPresent()) {
			return news.get();
		}
		
		return null;
	}

	@Override
	public void save(News news) {
		
		newsRepository.save(news);
		
	}

	@Override
	public void deleteById(int id) {
		
		newsRepository.deleteById(id);
		
	}

	@Override
	public List<News> findByCustomer(Customer theCustomer) {
	
		return newsRepository.findByCustomer(theCustomer);
	}

	@Override
	public News findByCustomerAndId(Customer theCustomer, int theId) {
	
		return newsRepository.findByCustomerAndId(theCustomer, theId);
	}

}
