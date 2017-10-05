package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.config.SecurityConfig;
import com.entity.User;
import com.service.EmailService;
import com.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage() {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {

		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfig.PASSWORD_STRENGHT);
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userService.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();

			return "redirect:/register";

		}

		emailService.sendEmail("biblioteka@onet.pl", user.getEmail(), "Welcome to out library",
				"Registration completed");

		return "redirect:/login";
	}

}
