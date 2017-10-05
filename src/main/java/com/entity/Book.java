package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Books")
public class Book extends BaseEntity {

	@Column(name="author")
	@NotNull
	@Size(min=3, max=255, message="Input author's name from {min} to {max}")
	private String author;
	
	@Column(name="title")
	@NotNull
	@Size(min=3, max=255, message="Input book's title from {min} to {max}")
	private String title;
	
	@Min(0)
	private Integer available;

	public Book() {
		
	}

	public Book(String author, String title, Integer available) {
		this.author = author;
		this.title = title;
		this.available = available;
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	public void decrementAvailable() {
		available--;
	}
}

