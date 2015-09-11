package com.gcit.lms.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.domain.*;

public class BookCopiesDAO extends BaseDAO{
	
	public BookCopiesDAO(Connection conn) throws Exception {
		super(conn);
	}

	public void create(BookCopies bookCopies) throws Exception {
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values(?,?,?)",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies() });
	}

	public void update(BookCopies bookCopies) throws Exception {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] { bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId() });

	}

	public void delete(BookCopies bookCopies) throws Exception {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId() });
	}

	public List<BookCopies> readAll() throws Exception{
		return (List<BookCopies>) readAll("select * from tbl_book_copies", null);
		
	}

	public BookCopies readOne(int bookId, int branchId) throws Exception {
		List<BookCopies> bookCopies = (List<BookCopies>) readFirstLevel("select * from tbl_book_copies where bookId = ? "
				+ "and branchId = ?", new Object[] {bookId, branchId});
		if(bookCopies!=null && bookCopies.size()>0){
			return bookCopies.get(0);
		}
		return null;
	}

	
	@Override
	public List<BookCopies> extractData(ResultSet rs) {
		List<BookCopies> bookCopies =  new ArrayList<BookCopies>();
		
		while(rs.next()){
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bookCopies.add(bc);
		}
		return bookCopies;
	}
	
	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) {
		List<BookCopies> bookCopies =  new ArrayList<BookCopies>();
		
		while(rs.next()){
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bookCopies.add(bc);
		}
		return bookCopies;
	}}

