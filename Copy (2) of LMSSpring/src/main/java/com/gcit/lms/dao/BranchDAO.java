package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BranchDAO extends BaseDAO<Branch>implements ResultSetExtractor<List<Branch>> {
	@Autowired
	BookDAO bdao;
	@Autowired
	BookLoanDAO bldao;

	private final String BR_COLLECTION = "branches";

	public void create(Branch lb) throws Exception {
		mongoOps.insert(lb, BR_COLLECTION);
	}

	public List<Branch> readAll() throws Exception {
		return mongoOps.findAll(Branch.class, BR_COLLECTION);
	}

	public Branch readOne(UUID branchId) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(branchId));
		return mongoOps.findOne(query, Branch.class, BR_COLLECTION);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		return null;
	}

	public List<Branch> readByBranchName(String searchString) throws Exception {
		searchString = "%" + searchString + "%";
		return (List<Branch>) template.query(
				"select * from tbl_library_branch where branchName like ? or branchAddress like ?",
				new Object[] { searchString, searchString }, this);
	}

	public List<Branch> readAll(int pageNo, int pageSize) throws Exception {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return mongoOps.findAll(Branch.class, BR_COLLECTION);

	}

	public void updateBranch(Branch lb) throws Exception {
		template.update("update tbl_library_branch set branchName=?, branchAddress=? where branchId=?",
				new Object[] { lb.getBranchName(), lb.getBranchAddress(), lb.getBranchId() });

	}

	public void deleteBranch(Branch lb) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true));
		mongoOps.findAndRemove(query, Branch.class, BR_COLLECTION);
	}

}