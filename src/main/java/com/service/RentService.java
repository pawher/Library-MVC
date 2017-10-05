package com.service;

import java.util.List;

import com.entity.Book;
import com.entity.Rent;
import com.entity.User;

public interface RentService {
	
	void createRent(User user, Book book);
	List<Rent> findAll();
	List<Rent> findByUserOrderByCreatedDateDesc(User user);
}
