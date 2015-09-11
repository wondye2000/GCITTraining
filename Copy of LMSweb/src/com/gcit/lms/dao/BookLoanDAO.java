package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.*;

public class BookLoanDAO extends BaseDAO{
	
	public BookLoanDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookLoan bookLoan) throws Exception {
		//dateIn?
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?,?,?,?,?)",
				new Object[] { bookLoan.getBook().getBookId(), bookLoan.getLibraryBranch().getBranchId(), 
				bookLoan.getBorrower().getCardNo(), bookLoan.getDateOut(), bookLoan.getDueDate() });
	}

	
	public void update(BookLoan bookLoan) throws Exception {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and cardNo = ? and branchId = ?",
				new Object[] {bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getDateIn(), bookLoan.getBook().getBookId(),
				bookLoan.getBorrower().getCardNo(), bookLoan.getLibraryBranch().getBranchId()});
	}

	//------------------------here----------------------------
	public void delete(BookLoan bookLoan) throws Exception {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookLoan.getBook().getBookId(), bookLoan.getLibraryBranch().getBranchId(), bookLoan.getBorrower().getCardNo() });
	}

	public List<BookLoan> readAll() throws Exception{
		return (List<BookLoan>) readAll("select * from tbl_book_loans", null);
		
	}

	public BookLoan readOne(int bookId, int cardNo, int branchId) throws Exception {
		List<BookLoan> bookLoans = (List<BookLoan>) readAll("select * from tbl_book_loans where bookId = ? and branchId = ?"
				+ " and cardNo = ?", new Object[] {bookId, branchId, cardNo});
		if(bookLoans!=null && bookLoans.size()>0){
			return bookLoans.get(0);
		}
		return null;
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) {
		List<BookLoan> bookLoans =  new ArrayList<BookLoan>();
		BookDAO bookdao = new BookDAO(conn);
		BranchDAO branchdao = new BranchDAO();
		BorrowerDAO borrowerdao = new BorrowerDAO();
		
		while(rs.next()){
			BookLoan bl = new BookLoan();
			bl.setDateIn(rs.getTimestamp("dateIn"));
			bl.setDateOut(rs.getTimestamp("dateOut"));
			bl.setDueDate(rs.getTimestamp("dueDate"));
			
			bl.setBook(bookdao.readOne(rs.getInt("bookId")));
			bl.setBorrower(borrowerdao.readOne(rs.getInt("cardNo")));
			bl.setLibraryBranch(branchdao.readOne(rs.getInt("branchId")));
			
			bookLoans.add(bl);
		}
		return bookLoans;
	}

	@Override
	public List<BookLoan> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<BookLoan> bookLoans =  new ArrayList<BookLoan>();
		
		while(rs.next()){
			BookLoan bl = new BookLoan();
			bl.setDateIn(rs.getTimestamp("dateIn"));
			bl.setDateOut(rs.getTimestamp("dateOut"));
			bl.setDueDate(rs.getTimestamp("dueDate"));
			
			//BookDAO bookdao = new BookDAO(getConnection());
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			bl.setBook(b);
			
			//LibraryBranchDAO lbdao = new LibraryBranchDAO(getConnection());
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(rs.getInt("branchId"));
			bl.setLibraryBranch(lb);
			
			//BorrowerDAO bdao = new BorrowerDAO(getConnection());
			Borrower bo = new Borrower();
			bo.setCardNo(rs.getInt("cardNo"));
			bl.setBorrower(bo);
			
			bookLoans.add(bl);
		}
		return bookLoans;
	}
}
