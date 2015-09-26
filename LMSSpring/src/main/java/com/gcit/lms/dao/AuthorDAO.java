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
import com.gcit.lms.domain.Genre;

@SuppressWarnings("unchecked")
public class AuthorDAO extends BaseDAO implements ResultSetExtractor<List<Author>> {

	@Autowired
	BookDAO bdao;
	// public AuthorDAO(Connection conn) {
	// super(conn);
	// TODO Auto-generated constructor stub
	// }

	public void createAuthor(Author author) throws ClassNotFoundException, SQLException {

		template.update("insert into tbl_author (authorName) values(?)", new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_author where authorId=?", new Object[] { author.getAuthorId() });
	}

	public List<Author> getAllAuthors(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		List<Author> authors = (List<Author>) template.query("select * from tbl_author LIMIT ? OFFSET ?",
				new Object[] { pageSize, (pageNo - 1) * pageSize }, this);
		return authors;
	}

	// public List<Author> readAll() throws ClassNotFoundException,
	// SQLException {
	// return readAll("select * from tbl_author", null);
	// }

	public Author getAuthorById(Author author) throws ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<Author>();
		authors = template.query("select * from tbl_author where authorId = ?", new Object[] { author.getAuthorId() },
				this);

		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		}
		return null;
	}

	public Author getAuthorByName(Author author) throws ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<Author>();
		authors = template.query("select * from tbl_author where authorName = ?",
				new Object[] { author.getAuthorName() }, this);

		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		}
		return null;
	}

	public List<Author> readAll() throws Exception {
		return template.query("select * from tbl_author", this);
	}

	@Override
	public List<Author> extractData(ResultSet rs) {

		List<Author> authors = new ArrayList<Author>();
		// BookDAO bdao = new BookDAO(conn);
		try {

			while (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));

				authors.add(a);

				// List<Book> books = (List<Book>) bdao.readFirstLevel(
				// "select * from tbl_book where bookId in (select bookId from
				// tbl_book_authors where authorId = ?)",
				// new Object[] { a.getAuthorId() });
				// a.setBooks(books);;

				// authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return authors;
	}

	/*
	 * public List extractDataFirstLevel(ResultSet rs) { List<Author> authors =
	 * new ArrayList<Author>();
	 * 
	 * try { while (rs.next()) { Author a = new Author();
	 * a.setAuthorId(rs.getInt("authorId"));
	 * a.setAuthorName(rs.getString("authorName")); authors.add(a); } } catch
	 * (SQLException e) { e.printStackTrace(); }
	 * 
	 * return authors; }
	 */

	public List<Author> getAuthorsByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";

		return template.query("select * from tbl_author where authorName like ?", new Object[] { searchString }, this);

	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_author", Integer.class);
	}
}
