package com.service;

import org.springframework.stereotype.Service;


public interface EmailService {
	
	void sendEmail(String fromAddress, String toAddress, String subject, String body);

}
