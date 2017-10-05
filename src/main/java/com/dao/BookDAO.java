package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long>{

}
