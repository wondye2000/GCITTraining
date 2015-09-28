package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.ResultSetExtractor;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>> {

	private final String PUB_COLLECTION = "publishers";

	
	public void createPublisher(Publisher pub) throws Exception {
		mongoOps.insert(pub, PUB_COLLECTION);
	}

	
	public List<Publisher> readAll() throws Exception {
		return mongoOps.findAll(Publisher.class, PUB_COLLECTION);
	}

	//read one
	public Publisher readOne(UUID publisherId) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(publisherId));
		return mongoOps.findOne(query, Publisher.class, PUB_COLLECTION);
	}
	//read for PAGINATION
	public List<Publisher> readAll(int pageNo, int pageSize) throws Exception{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return mongoOps.findAll(Publisher.class, PUB_COLLECTION);

	}
	//read by Search
	public List<Publisher> readByPublisherName(String searchString) throws Exception{
		searchString = "%"+searchString+"%";
		return (List<Publisher>) template.query("select * from tbl_publisher where publisherName or publisherAddress or publisherPhone like ?", new Object[] {searchString}, this);
	}
	
	//UPDATE
	public void updatePublisher(Publisher pub) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(pub.getPublisherId()));

		Update update = new Update();
		update.set("publisherName", pub.getPublisherName());
		update.set("publisherAddress", pub.getPublisherAddress());
		update.set("publisherPhone", pub.getPublisherPhone());

		//FindAndModifyOptions().returnNew(true) = newly updated document
		//FindAndModifyOptions().returnNew(false) = old document (not update yet)
		mongoOps.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Publisher.class, PUB_COLLECTION);
	}

	//DELETE
	public void deletePublisher(Publisher pub) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true));
		mongoOps.findAndRemove(query, Publisher.class, PUB_COLLECTION);
	}

	//RETURN List of Publishers
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		return null;
	}

}
