package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class GenreDAO extends BaseDAO<Genre>implements ResultSetExtractor<List<Genre>> {

	@Autowired
	BookDAO bdao;

	public void create(Genre genre) throws Exception {
		template.update("insert into tbl_genre (genre_name) values(?)", new Object[] { genre.getGenreName() });
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_genre where genre_Id=?", new Object[] { genre.getGenreId() });
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getGenresByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";

		return template.query("select * from tbl_genre where genre_Name like ?", new Object[] { searchString }, this);

	}

	public void update(Genre genre) throws Exception {
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void delete(Genre genre) throws Exception {
		template.update("delete from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}

	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_genre (genre_Name) values(?)", new Object[] { genre.getGenreName() });
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Genre> template.query() throws
	 * Exception { return (List<Genre>) template.query("select * from tbl_genre"
	 * , null);
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws Exception {
		return (List<Genre>) template.query("select * from tbl_genre", this);

	}

	@SuppressWarnings("unchecked")
	public Genre getGenreById(Genre genre) throws ClassNotFoundException, SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		genres = template.query("select * from tbl_genre where genre_Id = ?", new Object[] { genre.getGenreId() },
				this);

		if (genres != null && genres.size() > 0) {
			return genres.get(0);
		}
		return null;
	}

	public Genre readOne(int genreId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Genre> genres = template.query("select * from tbl_genre where genre_id = ?", new Object[] { genreId },
				this);
		if (genres != null && genres.size() > 0) {
			return genres.get(0);
		}
		return null;
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_genre", Integer.class);
	}

	public List<Genre> getAllGenres(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		List<Genre> genres = (List<Genre>) template.query("select * from tbl_genre LIMIT ? OFFSET ?",
				new Object[] { pageSize, (pageNo - 1) * pageSize }, this);
		return genres;
	}

	@Override
	public List<Genre> extractData(ResultSet rs) {
		List<Genre> genres = new ArrayList<Genre>();
		// BookDAO bDao = new BookDAO(getConnection());

		try {
			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_id"));
				g.setGenreName(rs.getString("genre_name"));
				// @SuppressWarnings("unchecked")
				// List<Book> books = (List<Book>) bDao
				// .readFirstLevel(
				// "select * from tbl_books where bookId In"
				// + "(select bookId from tbl_book_authors where authorId=?)",
				// new Object[] { rs.getInt("authorId") });
				// a.setBooks(books);
				genres.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
	}

	/** not necessary for genres. */
	/*
	 * @Override public List<Genre> extractDataFirstLevel(ResultSet rs) {
	 * List<Genre> genres = new ArrayList<Genre>();
	 * 
	 * try { while (rs.next()) { Genre g = new Genre();
	 * g.setGenreId(rs.getInt("genre_id"));
	 * g.setGenreName(rs.getString("genre_name"));
	 * 
	 * genres.add(g); } } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return genres; }
	 */
}
