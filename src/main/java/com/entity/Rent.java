package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Rents")
public class Rent extends BaseEntity {
	
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable=false)
	private Book book;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable=false)
	private Status status;

	public Rent() {
	
	}

	public Rent(User user, Book book) {
		this.user = user;
		this.book = book;
		
		createdDate = new Date();
		status = Status.RENTED;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
