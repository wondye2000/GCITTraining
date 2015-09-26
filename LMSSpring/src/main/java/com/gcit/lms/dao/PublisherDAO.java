package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>> {

	/*
	 * public PublisherDAO(Connection conn) { super(conn); // TODO
	 * Auto-generated constructor stub }
	 */

	public PublisherDAO() {
		// TODO Auto-generated constructor stub
	}

	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values(?, ?, ?)",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone() });
	}

	public List<Publisher> getPublishersByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";

		return template.query("select * from tbl_publisher where publisherName like ?", new Object[] { searchString },
				this);

	}
	/*
	 * public List<Publisher> template.query() throws ClassNotFoundException,
	 * SQLException { return template.query("select * from tbl_publisher",
	 * this); }
	 */

	public Publisher getPublisherById(int publisherId) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[] { publisherId },
				this);

		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		}
		return null;
	}

	public List<Publisher> getAllPublishers(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		List<Publisher> publishers = (List<Publisher>) template.query("select * from tbl_publisher LIMIT ? OFFSET ?",
				new Object[] { pageSize, (pageNo - 1) * pageSize }, this);
		return publishers;
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_publisher where publisherId=?", new Object[] { publisher.getPublisherId() });
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) {
		List<Publisher> publishers = new ArrayList<Publisher>();

		try {
			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherPhone(rs.getString("publisherPhone"));

				publishers.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publishers;
	}

	/*
	 * @Override public List extractDataFirstLevel(ResultSet rs) {
	 * List<Publisher> publishers = new ArrayList<Publisher>();
	 * 
	 * try { while (rs.next()) { Publisher p = new Publisher();
	 * p.setPublisherId(rs.getInt("publisherId"));
	 * p.setPublisherAddress(rs.getString("publisherAddress"));
	 * p.setPublisherName(rs.getString("publisherName"));
	 * p.setPublisherPhone(rs.getString("publisherPhone"));
	 * 
	 * publishers.add(p); } } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return publishers; }
	 */

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update(
				"update tbl_publisher set publisherName = ?, publisherPhone = ?, publisherAddress = ? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAll() throws Exception {
		return (List<Publisher>) template.query("select * from tbl_publisher", this);

	}

	public Publisher readOne(int publisherId) throws Exception {

		List<Publisher> publisher = template.query("select * from tbl_publisher where publisherId=?",
				new Object[] { publisherId }, this);
		if (publisher != null && publisher.size() > 0) {
			return publisher.get(0);
		}
		return null;
	}

	public Publisher getPublisherById(Publisher publisher) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers = template.query("select * from tbl_publisher where publisherId = ?",
				new Object[] { publisher.getPublisherId() }, this);

		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		}
		return null;
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_publisher", Integer.class);
	}
}
