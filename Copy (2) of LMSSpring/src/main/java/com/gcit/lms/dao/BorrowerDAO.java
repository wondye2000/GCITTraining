package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;

public class BorrowerDAO extends BaseDAO<Borrower>implements ResultSetExtractor<List<Borrower>> {

	@Autowired
	BookLoanDAO bldao;

	private final String BR_COLLECTION = "borrowers";

	public void create(Borrower borrow) throws Exception {
		mongoOps.insert(borrow, BR_COLLECTION);
	}

	public List<Borrower> readAll() throws Exception {
		return mongoOps.findAll(Borrower.class, BR_COLLECTION);
	}

	public Borrower readOne(UUID cardNo) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(cardNo));
		return mongoOps.findOne(query, Borrower.class);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		return null;
	}

	public List<Borrower> readByBorrowerName(String searchString) throws Exception {
		searchString = "%" + searchString + "%";
		return (List<Borrower>) template.query("select * from tbl_borrower where name like ?",
				new Object[] { searchString }, this);
	}

	public List<Borrower> readAll(int pageNo, int pageSize) throws Exception {
		return mongoOps.findAll(Borrower.class, BR_COLLECTION);
	}

	public void update(Borrower borrow) throws Exception {
		template.update("update tbl_borrower set name=?, address=?, phone=? where cardNo=?",
				new Object[] { borrow.getName(), borrow.getAddress(), borrow.getPhone(), borrow.getCardNo() });

	}

	public void delete(Borrower borrow) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true));
		mongoOps.findAndRemove(query, Borrower.class, BR_COLLECTION);
	}

}