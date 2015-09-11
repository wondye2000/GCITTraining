package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void createGenre(Genre genre) throws Exception {
		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws Exception {
		save("delete from tbl_genre where genreId = ?",
				new Object[] { genre.getGenreId() });
	}

	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws Exception{
		return (List<Genre>) readAll("select * from tbl_genre", null);
		
	}

	public Genre readOne(int genreId) throws Exception {
		List<Genre> genres = (List<Genre>) readAll("select * from tbl_genre where genre_id = ?", new Object[] {genreId});
		if(genres!=null && genres.size()>0){
			return genres.get(0);
		}
		return null;
	}


	@Override
	public List extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		BookDAO bDao = new BookDAO(conn);
		
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bDao.readFirstLevel("select * from tbl_book where bookId In"
					+ "(select bookId from tbl_book_genres where genre_id=?)", new Object[] {rs.getInt("genre_id")});
			g.setBooks(books);
			
			genres.add(g);
		}
		
		return genres;
	}
	
	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres =  new ArrayList<Genre>();
		
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			
			genres.add(g);
		}
		return genres;
	}
}
