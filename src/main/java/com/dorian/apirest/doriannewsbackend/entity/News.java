package com.dorian.apirest.doriannewsbackend.entity;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="source_name")
	private String sourceName;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="url")
	private String url;
	
	@Column(name="url_to_image")
	private String urlToImage;
	
	@Column(name="published_at")
	private GregorianCalendar publishedAt;
	
	@Column(name="content")
	private String content;
	
	public News() {
		
	}
	
	public News(String sourceName, String author, String title, String description, String url, String urlToImage,
			GregorianCalendar publishedAt, String content) {
		this.sourceName = sourceName;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public GregorianCalendar getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(GregorianCalendar publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", sourceName=" + sourceName + ", author=" + author + ", title=" + title
				+ ", description=" + description + ", url=" + url + ", urlToImage=" + urlToImage + ", publishedAt="
				+ publishedAt + ", content=" + content + "]";
	}

	
}
