package com.service;

import java.util.List;

import com.entity.Book;
import com.entity.User;

public interface BookService {
	
	void save(Book book);
	List<Book> findAll();
	void delete(Long id);
	Book findOne(Long id);
}
