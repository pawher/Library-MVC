package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Rent;
import com.entity.User;

@Repository
public interface RentDAO extends JpaRepository<Rent, Long> {
	
	//zwraca liste wypozyczen daneto uzytkownika posortowana ze wzgledu na data dodania wypozyczenia od daty najwczesniejszej
	
	List<Rent> findByUserOrderByCreatedDateDesc(User user);

}
