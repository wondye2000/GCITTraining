package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Publisher;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PublisherDAO extends BaseDAO {

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	public List<Publisher> readAll() throws ClassNotFoundException,
			SQLException {
		return readAll("select * from tbl_publisher", null);
	}

	
	public Publisher getPublisherById(int publisherId) throws ClassNotFoundException,
			SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers = readAll("select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId });

		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List<?> extractData(ResultSet rs) {
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


	@Override
	public List extractDataFirstLevel(ResultSet rs) {
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
}