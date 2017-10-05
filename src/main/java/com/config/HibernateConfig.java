package com.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource(value={"classpath:hibernate.properties"})
@EnableJpaRepositories(basePackages = "com.dao")
public class HibernateConfig {
	
	@Autowired
	private Environment settings;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(settings.getRequiredProperty("jdbc.driver.class.name"));
		dataSource.setPassword(settings.getRequiredProperty("jdbc.password"));
		dataSource.setUsername(settings.getRequiredProperty("jdbc.user.name"));
		dataSource.setUrl(settings.getRequiredProperty("jdbc.url"));
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", settings.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", settings.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", settings.getProperty("hibernate.format_sql"));
		properties.put("hibernate.generate_statistics", settings.getProperty("hibernate.generate_statistics"));
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.entity");
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setJpaProperties(properties);
		factoryBean.setDataSource(dataSource());
		
		factoryBean.afterPropertiesSet();
		
		return factoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() { 
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}
}
