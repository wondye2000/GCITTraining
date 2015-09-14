package com.gcit.lms.service;

import java.sql.Connection;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Publisher;

public class AdministratorService {

	public void createAuthor(Author author) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author Name cannot be empty or more than 45 Chars");
			} else {
				AuthorDAO adao = new AuthorDAO(conn);
				adao.create(author);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateAuthor(Author author) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author Name cannot be empty or more than 45 Chars");
			} else {
				AuthorDAO adao = new AuthorDAO(conn);
				adao.update(author);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deleteAuthor(Author author) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (author == null) {
				throw new Exception("Please delete a valid author");
			} else {
				AuthorDAO adao = new AuthorDAO(conn);
				adao.delete(author);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createBook(Book book) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book title cannot be empty or more than 45 Chars");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.create(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateBook(Book book) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book title cannot be empty or more than 45 Chars");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.update(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deleteBook(Book book) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (book == null) {
				throw new Exception("Please delete a valid book");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.delete(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createPublisher(Publisher publisher) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception(
						"Publisher name cannot be empty or more than 45 Chars");
			} else if (publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception(
						"Publisher address cannot be empty or more than 45 Chars");
			} else if (publisher.getPublisherPhone() == null
					|| publisher.getPublisherPhone().length() == 0
					|| publisher.getPublisherPhone().length() > 45) {
				throw new Exception(
						"Publisher phone cannot be empty or more than 45 Chars");
			} else {
				PublisherDAO pdao = new PublisherDAO(conn);
				pdao.create(publisher);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updatePublisher(Publisher publisher) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception(
						"Publisher name cannot be empty or more than 45 Chars");
			} else if (publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception(
						"Publisher address cannot be empty or more than 45 Chars");
			} else {
				PublisherDAO pdao = new PublisherDAO(conn);
				pdao.update(publisher);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deletePublisher(Publisher publisher) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (publisher == null) {
				throw new Exception("Please delete a valid publisher");
			} else {
				PublisherDAO pdao = new PublisherDAO(conn);
				pdao.delete(publisher);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createLibraryBranch(Branch libraryBranch)
			throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (libraryBranch == null || libraryBranch.getBranchName() == null
					|| libraryBranch.getBranchName().length() == 0
					|| libraryBranch.getBranchName().length() > 45) {
				throw new Exception(
						"branch name cannot be empty or more than 45 Chars");
			}
			if (libraryBranch.getBranchAddress() == null
					|| libraryBranch.getBranchAddress().length() == 0
					|| libraryBranch.getBranchAddress().length() > 45) {
				throw new Exception(
						"branch address cannot be empty or more than 45 Chars");
			} else {
				BranchDAO lbdao = new BranchDAO(conn);
				lbdao.create(libraryBranch);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateLibraryBranch(Branch libraryBranch)
			throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (libraryBranch == null || libraryBranch.getBranchName() == null
					|| libraryBranch.getBranchName().length() == 0
					|| libraryBranch.getBranchName().length() > 45) {
				throw new Exception(
						"branch name cannot be empty or more than 45 Chars");
			}
			if (libraryBranch.getBranchAddress() == null
					|| libraryBranch.getBranchAddress().length() == 0
					|| libraryBranch.getBranchAddress().length() > 45) {
				throw new Exception(
						"branch address cannot be empty or more than 45 Chars");
			} else {
				BranchDAO lbdao = new BranchDAO(conn);
				lbdao.update(libraryBranch);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deleteLibraryBranch(Branch libraryBranch)
			throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (libraryBranch == null) {
				throw new Exception("delete a valid library branch");
			} else {
				BranchDAO lbdao = new BranchDAO(conn);
				lbdao.delete(libraryBranch);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createBorrower(Borrower borrower) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0
					|| borrower.getName().length() > 45) {
				throw new Exception(
						"borrower name cannot be empty or more than 45 Chars");
			} else if (borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getAddress().length() > 45) {
				throw new Exception(
						"borrower address cannot be empty or more than 45 Chars");
			} else if (borrower.getPhone() == null
					|| borrower.getPhone().length() == 0
					|| borrower.getPhone().length() > 45) {
				throw new Exception(
						"borrower phone cannot be empty or more than 45 Chars");
			} else {
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.create(borrower);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void updateBorrower(Borrower borrower) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0
					|| borrower.getName().length() > 45) {
				throw new Exception(
						"borrower name cannot be empty or more than 45 Chars");
			} else if (borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getAddress().length() > 45) {
				throw new Exception(
						"borrower address cannot be empty or more than 45 Chars");
			} else if (borrower.getPhone() == null
					|| borrower.getPhone().length() == 0
					|| borrower.getPhone().length() > 45) {
				throw new Exception(
						"borrower phone cannot be empty or more than 45 Chars");
			} else {
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.update(borrower);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deleteBorrower(Borrower borrower) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (borrower == null) {
				throw new Exception("please choose a valid borrower");
			} else {
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.delete(borrower);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void overrideDueDate(BookLoan bookLoan) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (bookLoan == null) {
				throw new Exception("please provide a valid book loan");
			} else {
				BookLoanDAO bdao = new BookLoanDAO(conn);
				bdao.update(bookLoan);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

}