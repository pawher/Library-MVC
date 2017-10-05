package com.controller;

import java.security.Principal;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Book;
import com.entity.Rent;
import com.entity.Role;
import com.entity.User;
import com.service.BookService;
import com.service.RentService;
import com.service.UserService;

@Controller
public class RentController {

	@Autowired
	private RentService rentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	//old way for logging
	private final static Logger LOGGER = Logger.getLogger(RentController.class.getName());
	
	@RequestMapping(value = "/rents", method = RequestMethod.GET) 
	public String getRentsPage(Model model, Principal principal) {
		
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		List<Rent> rents;
		
		if(user.getRole() == Role.CUSTOMER) {
			rents = rentService.findByUserOrderByCreatedDateDesc(user);
		} else {
			rents = rentService.findAll();
		}
		
		model.addAttribute("rentsList", rents);
		
		return "rents";
	}
	
	@RequestMapping(value = "/rent/book/{bookId}", method = RequestMethod.GET) 
 	public String createRent(@PathVariable Long bookId, Principal principal) {
		
		LOGGER.log(Level.INFO, String.format("Executing method createRent for bookId = %d", bookId));
		System.out.println(String.format("Executing method createRent for bookId = %d", bookId));
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		Book book = bookService.findOne(bookId);
		rentService.createRent(user, book);
		
		return "redirect:/rents";
		
	}
}
