package com.gcit.lms.domain;

import java.sql.Date;

public class BookLoans {

	private Borrower borrow;
	private Book book;
	private Branch branch;
	private Date dateOut;
	private Date dueDate;
	private Date dateIn;

	public Borrower getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrower borrow) {
		this.borrow = borrow;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

}
