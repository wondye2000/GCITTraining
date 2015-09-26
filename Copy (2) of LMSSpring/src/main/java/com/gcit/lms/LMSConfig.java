package com.gcit.lms;

import java.net.UnknownHostException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.service.AdministratorService;
import com.mongodb.MongoClient;

@EnableTransactionManagement
@Configuration
public class LMSConfig {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://localhost:3306/library";
	private static String userName = "root";
	private static String pwd = "root";
	
	@Bean
	AdministratorService service(){
		AdministratorService service = new AdministratorService();
		return service;
	}
	
@Bean
public AuthorDAO authorDAO(){
return new AuthorDAO();
}
	
	@Bean
	public BookDAO bookDAO(){
		return new BookDAO();
	}
	
	@Bean
	public PublisherDAO publisherDAO(){
		return new PublisherDAO();
	}
	
	@Bean
	public GenreDAO genreDAO(){
		return new GenreDAO();
	}
	
	@Bean
	public BranchDAO branchDAO(){
		return new BranchDAO();
	}
	@Bean
	public BookLoanDAO bookLoanDAO(){
		return new BookLoanDAO();
	}
	@Bean
	public BorrowerDAO borrowerDAO(){
		return new BorrowerDAO();
	}
	@Bean
		public BookCopiesDAO bookCopiesDAO(){
		return new BookCopiesDAO();
	}
	
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(dbURL);
		ds.setUsername(userName);
		ds.setPassword(pwd);
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager txManager1(){
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		return tx;
	}
	
	@Bean
	public JdbcTemplate template(){
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
}
