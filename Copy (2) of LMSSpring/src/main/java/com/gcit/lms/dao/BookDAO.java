package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookDAO extends BaseDAO<Book>implements ResultSetExtractor<List<Book>> {

	@Autowired
	PublisherDAO pdao;
	@Autowired
	AuthorDAO adao;
	@Autowired
	GenreDAO gdao;

	/*public void createBook(final Book book) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		System.out.println(book.getPublisher().getPublisherId());
		System.out.println(book.getTitle());
		template.update(
				new PreparedStatementCreator() {
	    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	    	            PreparedStatement pst =
	    	                con.prepareStatement("insert into tbl_book (title, pubId) values(?, ?)"
	    	                		, new String[] {"bookId"});
	    	            pst.setString(1, book.getTitle());
	    	            pst.setInt(2, book.getPublisher().getPublisherId());
	    	            return pst;
	    	        }
	    	    }, keyHolder);
		
		//template.

		

		int bookId = keyHolder.getKey().intValue();
		for (Author a : book.getAuthors()) {
			template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for (Genre g : book.getGenres()) {
			template.update("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
					new Object[] { bookId, g.getGenreId() });
		}

	}*/

	private final String BK_COLLECTION = "Books";

	public void createBook(final Book book) throws Exception {
		mongoOps.insert(book, BK_COLLECTION);
	}

	public void updateBook(Book book) throws Exception {
		Query query = new Query(Criteria.where("authorid").is(book.getBookId()));
		Book oldBook = mongoOps.findOne(query, Book.class, BK_COLLECTION);

		oldBook.setTitle(book.getTitle());
		mongoOps.save(oldBook, BK_COLLECTION);
	}

	public void deleteBook(Book book) throws Exception {
		Query query = new Query(Criteria.where("_id").is(book.getBookId()));
		mongoOps.remove(query, BK_COLLECTION);
	}

	public List<Book> readAllBooks() throws Exception {
		return mongoOps.findAll(Book.class, BK_COLLECTION);
	}

	public Book readOne(UUID bookId) throws Exception {
		Query query = new Query(Criteria.where("_id").is(bookId));
		return this.mongoOps.findOne(query, Book.class, BK_COLLECTION);
	}

	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {

		
		List<Book> booksList = new ArrayList<Book>();

		
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			/*try {
				book.setPublisher(pdao.readOne(rs.getInt("pubId")));
			} catch (Exception e) {
				e.printStackTrace();
			}*/

			
			List<Author> authors = template.query(
					"select * from tbl_author where authorId In"
							+ "(select authorId from tbl_book_authors where bookId=?)",
					new Object[] { rs.getInt("bookId") }, adao);
			book.setAuthors(authors);

			
			List<Genre> genres = template.query(
					"select * from tbl_genre where genre_id In"
							+ "(select genre_id from tbl_book_genres where bookId=?)",
					new Object[] { rs.getInt("bookId") }, gdao);
			book.setGenres(genres);

			// Cycling dependencies: extractDataFirstLevel VERY IMPORTANT
			// BASE DAO: based on BASE DAO, other DAO are going to react.
			// create connection in service layer and then pass instance to DAO

			booksList.add(book);
		}
		return booksList;
	}

	
	/*
	 * public List extractDataFirstLevel(ResultSet rs) { List<Book> books = new
	 * ArrayList<Book>(); //PublisherDAO pdao = new PublisherDAO(); try { while
	 * (rs.next()) { Book b = new Book(); PublisherDAO pdao = new
	 * PublisherDAO(); b.setBookId(rs.getInt("bookId"));
	 * 
	 * b.setTitle(rs.getString("title"));
	 * b.setPublisher(pdao.getPublisherById(rs.getInt("pubId"))); books.add(b);
	 * } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ClassNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } return books; }
	 */

	public List<Book> getBooksByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";
		return template.query("select * from tbl_book where title like ?", new Object[] { searchString }, this);
	}

	// list of boon in given branch
	public List<Book> branchBooks(int branchId) {
		return template.query("SELECT * FROM tbl_book JOIN tbl_book_copies ON tbl_book.bookId = tbl_book_copies.bookID"
				+ " WHERE branchId=? AND noOfCopies >=1", new Object[] { branchId }, this);
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_author", Integer.class);
	}

}
