package com.gcit.lms.domain;

import java.util.List;

public class Branch {

	public Branch() {

	}

	public Branch(int branchId, String branchName, String branchAddress) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	private int branchId;
	private String branchName;
	private String branchAddress;

	private List<Book> books;
	private List<BookLoans> loans;

	public List<Book> getBooks() {
		return books;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setLoans(List<BookLoans> loans) {
		this.loans = loans;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

}
