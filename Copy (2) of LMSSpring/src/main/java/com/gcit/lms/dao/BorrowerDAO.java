package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;

public class BorrowerDAO extends BaseDAO<Borrower>implements ResultSetExtractor<List<Borrower>> {

	/*
	 * public BorrowerDAO(Connection conn) throws Exception { super(conn); //
	 * TODO Auto-generated constructor stub }
	 */
	@Autowired
	BookDAO bdao;

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_borrower", Integer.class);
	}

	@SuppressWarnings("unchecked")
	public Borrower getBorrowerById(Borrower borrower) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		borrowers = (List<Borrower>) template.query("select * from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardNo() }, this);

		if (borrowers != null && borrowers.size() > 0) {
			return borrowers.get(0);
		}
		return null;
	}

	public void create(Borrower borrower) throws Exception {
		template.update("insert into tbl_borrower (name, address, phone) values(?, ?, ?)",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> getAllBorrowers(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Borrower>) template.query("select * from tbl_borrower", this);
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Borrower> template.query()
	 * throws ClassNotFoundException, SQLException { return (List<Borrower>)
	 * template.query("select * from tbl_borrower", null); }
	 */

	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_borrower (name) values(?)", new Object[] { borrower.getName() });
	}

	public void update(Borrower borrower) throws Exception {
		template.update("update tbl_borrower set name = ?, phone = ?, address = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });

	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws Exception {
		return (List<Borrower>) template.query("select * from tbl_borrower", this);

	}

	@SuppressWarnings("unchecked")
	public List<Borrower> getBorrowersByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";

		return (List<Borrower>) template.query("select * from tbl_borrower where name like ?",
				new Object[] { searchString }, this);
	}

	public void delete(Borrower borrower) throws Exception {
		template.update("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	@SuppressWarnings("unchecked")
	// public List<Borrower> template.query() throws Exception {
	// return (List<Borrower>) read("select * from tbl_borrower", null);

	// }

	public Borrower readOne(int cardNo) throws Exception {
		@SuppressWarnings("unchecked")
		List<Borrower> borrowers = (List<Borrower>) template.query("select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo }, this);
		if (borrowers != null && borrowers.size() > 0) {
			return borrowers.get(0);
		}
		return null;
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {

		// create a list
		List<Borrower> borrower = new ArrayList<Borrower>();

		// populate the list
		while (rs.next()) {

			Borrower borrow = new Borrower();
			borrow.setCardNo(rs.getInt("cardNo"));
			borrow.setName(rs.getString("name"));
			borrow.setAddress(rs.getString("address"));
			borrow.setPhone(rs.getString("phone"));

			// List<BookLoans> bookLoans = template.query("select * from
			// tbl_book_loans where cardNo = ?"
			// , new Object[] {rs.getInt("cardNo")}, bookLoanDAO);
			// borrow.setBookLoans(bookLoans);

			borrower.add(borrow);
		}
		return borrower;
	}

	// @Override
	/*
	 * public List<Borrower> extractData(ResultSet rs) { List<Borrower>
	 * borrowers = new ArrayList<Borrower>(); //BookDAO bDao = new
	 * BookDAO(conn);
	 * 
	 * while (rs.next()) { Borrower b = new Borrower();
	 * b.setCardNo(rs.getInt("cardNo")); b.setName(rs.getString("name"));
	 * b.setAddress(rs.getString("address")); b.setPhone(rs.getString("phone"));
	 * 
	 * @SuppressWarnings("unchecked") List<Book> books = (List<Book>)
	 * bdao.readFirstLevel( "select * from tbl_book where bookId In" +
	 * "(select bookId from tbl_book_loans where cardNo=?)", new Object[] {
	 * rs.getInt("cardNo") }); b.setBooks(books); borrowers.add(b); }
	 * 
	 * return borrowers; }
	 */

	/*
	 * @Override public List<Borrower> extractDataFirstLevel(ResultSet rs) {
	 * List<Borrower> borrowers = new ArrayList<Borrower>();
	 * 
	 * try { while (rs.next()) { Borrower b = new Borrower();
	 * b.setCardNo(rs.getInt("cardNo")); b.setName(rs.getString("name"));
	 * b.setAddress(rs.getString("address")); b.setPhone(rs.getString("phone"));
	 * 
	 * borrowers.add(b); } } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return borrowers; }
	 */

}