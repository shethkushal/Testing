package com.cartracker.service;


import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:externalProps.properties")
public class JPAConfig {
	
	@Autowired
	Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean emf(){
		//can use : emf.setPersistenceXmlLocation(persistenceXmlLocation);
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("com.cartracker.service");
		emf.setJpaProperties(jpaProperties());
		return emf;
	}
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(env.getProperty("db.Url"));
//		ds.setUsername(env.getProperty("db.Username"));
//		ds.setPassword(env.getProperty("db.Password"));
		ds.setUsername("root");
		ds.setPassword("");
		return ds;	
	}
	
	@Bean
	public PlatformTransactionManager txnManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
	
	private Properties jpaProperties(){
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		return prop;
	}
}