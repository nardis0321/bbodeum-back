package com.bbodeum.container;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ApplicationContext {

	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();

		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"); //sql구문도 추적
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setMinimumIdle(1);

		return config;
	}
	
	@Bean
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
	
	//JPA Config
//	@Bean
	public JpaVendorAdapter dbJpaVendorAdapter() {
	    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	    adapter.setDatabasePlatform("com.bbodeum.config.ExtendedOracleDialect"); // Dialect 연결
	    adapter.setDatabase(Database.ORACLE);

	    return adapter;
	}

//	@Bean
	public EntityManagerFactory dbEntityManagerFactory() {
		// ??
		//Specify the JDBC DataSource that the JPA persistence provider is supposedto use for accessing the database. This is an alternative to keeping theJDBC configuration in persistence.xml, passing in a Spring-managedDataSource instead. 
		HikariDataSource dataSource = dataSourceHikari();
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(dataSource);
	    entityManagerFactoryBean.setPersistenceUnitName("oracleEntity");
	    entityManagerFactoryBean.setPackagesToScan("com.bbodeum");
	    entityManagerFactoryBean.setJpaVendorAdapter(dbJpaVendorAdapter()); // Adapter 연결
	    entityManagerFactoryBean.afterPropertiesSet();

	    return entityManagerFactoryBean.getObject();
	}
}
