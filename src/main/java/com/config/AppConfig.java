package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.scheduler.AdvService;

@Configuration
@ComponentScan(basePackages = "com")
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter {

	public final static String EMAIL_FROM = "pawel89e@gmail.com";

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(EMAIL_FROM);
		mailSender.setPassword("Wyspawyspa11");
		mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
		mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
		return mailSender;
	}

	@Bean
	public AdvService getAdvService() {
		return new AdvService();
	}

}