package com.dorian.apirest.doriannewsbackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorian.apirest.doriannewsbackend.entity.News;
import com.dorian.apirest.doriannewsbackend.service.NewsService;

@RestController
@RequestMapping("/api")
public class NewsRestController {
	
	@Autowired
	private NewsService newsService;

	@GetMapping("/news")
	public List<News> findAll(){
		
		List<News> newss = newsService.findAll();
		
		return newss;
	}
	
	
	@GetMapping("/news/{theId}")
	public News getNewsById(@PathVariable int theId) {
		
		News news = newsService.findById(theId);
		
		return news;
		
	}
	
	@PostMapping("/news")
	public News addNews(@RequestBody News theNews) {
		
		theNews.setId(0);
		
		newsService.save(theNews);
		
		return theNews;
		
	}
	
	
	@PutMapping("/news")
	public News updateNews(@RequestBody News theNews) {
		
		newsService.save(theNews);
		
		return theNews;
		
	}
	
	
	@DeleteMapping("/news/{theId}")
	public String deleteNews(@PathVariable int theId) {
		
		News news = newsService.findById(theId);
		
		if(news == null) {
			throw new RuntimeException("News id not found - " + theId);
		}
		
		newsService.deleteById(theId);
		
		return "Deleted new id - " + theId;
	}
	
}
