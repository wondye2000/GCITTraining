package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AdministratorService {

	@Autowired
	BasicDataSource ds;

	@Autowired
	AuthorDAO adao;
	@Autowired
	BookDAO bdao;
	@Autowired
	PublisherDAO pdao;
	@Autowired
	BorrowerDAO bodao;
	@Autowired
	BranchDAO brdao;
	@Autowired
	GenreDAO gdao;

	public void createAuthor(Author author) throws Exception {
		if (author.getAuthorName() != null) {
			if (author.getAuthorName().length() > 45) {
				throw new Exception("Author Name cannot be more than 45 chars");
			} else {
				adao.createAuthor(author);
			}
		}

	}

	public void createBorrower(Borrower borrower) throws Exception {

		if (borrower.getName() != null || borrower.getAddress() != null || borrower.getPhone() != null) {
			if (borrower.getName().length() > 45) {
				throw new Exception("Field cannot be more than 45 chars");
			} else {
				bodao.create(borrower);
			}

		}
	}

	public void createBranch(Branch branch) throws Exception {
		if (branch.getBranchName() != null) {
			if (branch.getBranchName().length() > 45) {
				throw new Exception("Branch Name cannot be more than 45 chars");
			} else {
				brdao.create(branch);
			}
		}

	}

	public void createPublisher(Publisher publisher) throws Exception {

		if (publisher.getPublisherName() != null) {
			if (publisher.getPublisherName().length() > 45) {
				throw new Exception("Publisher Name cannot be more than 45 chars");
			} else {
				pdao.createPublisher(publisher);
			}

		}
	}

	public void createGenre(Genre genre) throws Exception {

		if (genre.getGenreName() != null) {
			if (genre.getGenreName().length() > 45) {
				throw new Exception("Genre Name cannot be more than 45 chars");
			} else {
				gdao.createGenre(genre);
			}
		}

	}

	public void createBook(Book book) throws Exception {
		if (book.getTitle() != null) {
			if (book.getTitle().length() > 45) {
				throw new Exception("Book Title cannot be more than 45 chars");
			} else {
				bdao.createBook(book);
			}
		}
	}

	public List<Author> getAuthors(int pageNo, int pageSize) throws Exception {
		return adao.getAllAuthors(pageNo, pageSize);
	}

	public List<Borrower> getBorrowers(int pageNo, int pageSize) throws Exception {

		return bodao.getAllBorrowers(pageNo, pageSize);

	}

	public List<Branch> getBranch(int pageNo, int pageSize) throws Exception {

		return brdao.getAllBranch(pageNo, pageSize);

	}

	public List<Publisher> getPublisher(int pageNo, int pageSize) throws Exception {
		return pdao.getAllPublishers(pageNo, pageSize);

	}

	public List<Book> getBooks(int pageNo, int pageSize) throws Exception {

		return bdao.getAllBooks(pageNo, pageSize);

	}

	public List<Genre> getGenres(int pageNo, int pageSize) throws Exception {
		return gdao.getAllGenres(pageNo, pageSize);

	}

	public List<Author> getAuthors() throws Exception {

		return adao.readAll();

	}

	public List<Borrower> getBorrowers() throws Exception {

		return bodao.readAll();

	}

	public List<Branch> getBranch() throws Exception {

		return brdao.readAll();

	}

	public List<Publisher> getPublishers() throws Exception {

		return pdao.readAll();

	}

	public List<Genre> getGenres() throws Exception {

		return gdao.readAll();

	}

	public Author getAuthorByID(int authorId) throws Exception {
		Author author = new Author();
		author.setAuthorId(authorId);

		return adao.getAuthorById(author);

	}

	public Borrower getBorrowerByID(int borrowerId) throws Exception {

		Borrower borrower = new Borrower();
		borrower.setCardNo(borrowerId);

		return bodao.getBorrowerById(borrower);

	}

	public Branch getBranchByID(int branchId) throws Exception {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		return brdao.getBranchById(branch);

	}

	public Book getBookByID(int bookId) throws Exception {
		Book book = new Book();
		book.setBookId(bookId);

		return bdao.getBookById(book);

	}

	public Publisher getPublisherByID(int publisherId) throws Exception {

		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);

		return pdao.getPublisherById(publisher);

	}

	public Genre getGenreByID(int genreId) throws Exception {
		Genre genre = new Genre();
		genre.setGenreId(genreId);

		return gdao.getGenreById(genre);

	}

	public void updateAuthor(Author author) throws Exception {

		if (author.getAuthorName() != null) {
			if (author.getAuthorName().length() > 45) {
				throw new Exception("Author Name cannot be more than 45 chars");
			} else {
				adao.updateAuthor(author);
			}

		}
	}

	public void updateBorrower(Borrower borrower) throws Exception {

		if (borrower.getName() != null && borrower.getAddress() != null && borrower.getPhone() != null) {
			if (borrower.getName().length() > 45) {
				throw new Exception("Field cannot be more than 45 chars");
			} else {
				bodao.update(borrower);
			}
		}

	}

	public void updateBranch(Branch branch) throws Exception {

		if (branch.getBranchName() != null) {
			if (branch.getBranchName().length() > 45) {
				throw new Exception("Branch Name cannot be more than 45 chars");
			} else {
				brdao.updateBranch(branch);
			}
		}
	}

	public void updateGenre(Genre genre) throws Exception {

		if (genre.getGenreName() != null) {
			if (genre.getGenreName().length() > 45) {
				throw new Exception("Genre Name cannot be more than 45 chars");
			} else {
				gdao.updateGenre(genre);
			}

		}
	}

	public void updatePublisher(Publisher publisher) throws Exception {

		if (publisher.getPublisherName() != null) {
			if (publisher.getPublisherName().length() > 45) {
				throw new Exception("Publisher Name cannot be more than 45 chars");
			} else {
				pdao.updatePublisher(publisher);
			}

		}
	}

	public void updateBook(Book book) throws Exception {
		if (book.getTitle() != null) {
			if (book.getTitle().length() > 45) {
				throw new Exception("Title cannot be more than 45 chars");
			} else {
				bdao.updateBook(book);
			}

		}
	}

	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {

		adao.deleteAuthor(author);

	}

	public void deleteBorrower(Borrower borrower) throws Exception {

		bodao.delete(borrower);

	}

	public void deleteBranch(Branch branch) throws SQLException, ClassNotFoundException {

		brdao.deleteBranch(branch);

	}

	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {

		gdao.deleteGenre(genre);

	}

	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {

		pdao.deletePublisher(publisher);

	}

	public void deleteBook(Book book) throws SQLException, ClassNotFoundException {

		bdao.deleteBook(book);

	}

	public List<Author> searchAuthors(String searchString) throws ClassNotFoundException, SQLException {

		return adao.getAuthorsByName(searchString);

	}

	public List<Borrower> searchBorrowers(String searchString) throws Exception {

		return bodao.getBorrowersByName(searchString);

	}

	public List<Genre> searchGenres(String searchString) throws Exception {

		return gdao.getGenresByName(searchString);

	}

	public List<Publisher> searchPublishers(String searchString) throws ClassNotFoundException, SQLException {

		return pdao.getPublishersByName(searchString);

	}

	public List<Branch> searchBranchs(String searchString) throws Exception {

		return brdao.getBranchsByName(searchString);

	}

	public List<Book> searchBooks(String searchString) throws ClassNotFoundException, SQLException {

		return bdao.getBooksByName(searchString);

	}

	public int getAuthorsCount() throws SQLException, ClassNotFoundException {

		return adao.getCount();

	}
	
	public int getBooksCount() throws SQLException, ClassNotFoundException {

		return bdao.getCount();

	}

	public int getBorrowersCount() throws Exception {

		return bodao.getCount();

	}

	public int getBranchCount() throws Exception {

		return brdao.getCount();

	}

	public int getGenresCount() throws Exception {

		return gdao.getCount();

	}

	public int getPublishersCount() throws SQLException, ClassNotFoundException {

		return pdao.getCount();

	}
}
