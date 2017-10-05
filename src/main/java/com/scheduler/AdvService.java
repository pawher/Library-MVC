package com.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.config.AppConfig;
import com.entity.User;
import com.service.EmailService;
import com.service.UserService;

@Service
public class AdvService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Scheduled(fixedDelay = 10000)
	public void sendAdv() { 
		List<User> users = userService.findAll();
		
		if(users == null)
			return;
		
		for(User user : users) {
			emailService.sendEmail(AppConfig.EMAIL_FROM, user.getEmail(), "AD", "The best library in the city");
		}
	}

}
