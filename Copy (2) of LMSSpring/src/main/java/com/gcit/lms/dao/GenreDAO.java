package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class GenreDAO extends BaseDAO<Genre>implements ResultSetExtractor<List<Genre>> {

	@Autowired
	BookDAO bdao;

	private final String GE_COLLECTION = "genres";

	public void createGenre(Genre gen) throws Exception {
		mongoOps.insert(gen, GE_COLLECTION);
	}

	public void updateGenre(Genre gen) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(gen.getGenreId()));

		Update update = new Update();
		update.set("genreName", gen.getGenreName());

		mongoOps.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Genre.class, GE_COLLECTION);
	}

	public void deleteGenre(Genre gen) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true));

		mongoOps.findAndRemove(query, Genre.class, GE_COLLECTION);
	}

	public List<Genre> readAll() throws Exception {
		return mongoOps.findAll(Genre.class, GE_COLLECTION);
	}

	public Genre readOne(UUID genreId) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(genreId));
		return mongoOps.findOne(query, Genre.class, GE_COLLECTION);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		return null;
	}

	public List<Genre> readByGenreName(String searchString) throws Exception {
		searchString = "%" + searchString + "%";
		return (List<Genre>) template.query("select * from tbl_genre where genre_Name like ?",
				new Object[] { searchString }, this);
	}

	public List<Genre> readAll(int pageNo, int pageSize) throws Exception {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return mongoOps.findAll(Genre.class, GE_COLLECTION);
	}

}
