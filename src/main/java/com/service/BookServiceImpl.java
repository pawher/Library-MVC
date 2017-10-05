package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDAO;
import com.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDao;
	
	@Override
	public void save(Book book) {
		bookDao.save(book);
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);
	}

	@Override
	public Book findOne(Long id) {
		return bookDao.findOne(id);
	}
	
	

}
