package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDAO;
import com.dao.RentDAO;
import com.entity.Book;
import com.entity.Rent;
import com.entity.User;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentDAO rentDao;

	@Autowired
	private BookDAO bookDao;

	@Override
	public void createRent(User user, Book book) {
		Rent rent = new Rent(user, book);
		rentDao.save(rent);
		book.decrementAvailable();
		bookDao.save(book);
	}

	@Override
	public List<Rent> findAll() {
		return rentDao.findAll();
	}

	@Override
	public List<Rent> findByUserOrderByCreatedDateDesc(User user) {
		return rentDao.findByUserOrderByCreatedDateDesc(user);
	}

}
