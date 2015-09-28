package com.gcit.lms.dao;

import java.sql.Connection;
import com.mongodb.DBObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

	private static final String AUTH_COLLECTION = "Authors";

	public void createAuthor(Author author) {
		template.update("insert into tbl_author (authorName) values(?)", new Object[] { author.getAuthorName() });
		mongoOps.save(author, AUTH_COLLECTION);
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
			Query query = new Query();
		query.addCriteria(Criteria.where("authorId").is(author.getAuthorId()));

		Update update = new Update();
		update.set("authorName", author.getAuthorName());
		mongoOps.updateFirst(query, update, Author.class);

	}

	public void deleteAuthor(Author author) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("authorid").exists(true));
		
		mongoOps.findAndRemove(query, Author.class, AUTH_COLLECTION);
	}
	
	
	public List<Author> readAll() throws Exception{
		return mongoOps.findAll(Author.class, AUTH_COLLECTION);
	}


	public Author readOne(UUID authorId) throws Exception {
		Query query = new Query(Criteria.where("_id").is(authorId));
        return this.mongoOps.findOne(query, Author.class, AUTH_COLLECTION);
	}


	public List<Author> readByAuthorName(String searchString) throws Exception{
		searchString = "/"+searchString+"/";
		Query query = new Query(Criteria.where("authorName").regex(searchString));
		return mongoOps.find(query, Author.class, AUTH_COLLECTION);
	}

	
	public List<Author> getAllAuthors(int pageNumber, int pageSize) throws Exception{
		//db.students.find().skip(pageNumber > 0 ? ((pageNumber-1)*nPerPage) : 0).limit(nPerPage).forEach( function(student) { print(student.name + "<p>"); } );
		return mongoOps.findAll(Author.class, AUTH_COLLECTION);
	}

	
	public long getCount() throws Exception{
		Query query = new Query(Criteria.where("authorid").exists(true));
		return mongoOps.count(query, Author.class);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
