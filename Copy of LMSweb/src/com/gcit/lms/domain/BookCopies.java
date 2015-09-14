package com.gcit.lms.domain;

public class BookCopies {
	private int bookId;
	private int branchId;
	private int noOfCopies;
	
	public BookCopies(){
		
	}

	public BookCopies(int bookId, int branchId, int noOfCopies) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return
	 */
	public int getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return
	 */
	public int getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

}
