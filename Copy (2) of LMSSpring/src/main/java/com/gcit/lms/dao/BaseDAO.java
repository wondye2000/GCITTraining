package com.gcit.lms.dao;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDAO<T> {
	@Autowired
	JdbcTemplate template;

	private int pageNo = -1;

	private int pageSize = 10;

	// public BaseDAO(Connection conn) {
	// this.conn = conn;
	// }

	/*
	 * public void save(String query, Object[] vals) throws SQLException,
	 * ClassNotFoundException { PreparedStatement pstmt =
	 * conn.prepareStatement(query);
	 * 
	 * if (vals != null) { int count = 1; for (Object o : vals) {
	 * pstmt.setObject(count, o); count++; } } pstmt.executeUpdate(); }
	 */

	/*
	 * public int saveWithID(String query, Object[] vals) throws SQLException,
	 * ClassNotFoundException { PreparedStatement pstmt =
	 * conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	 * 
	 * if (vals != null) { int count = 1; for (Object o : vals) {
	 * pstmt.setObject(count, o); count++; } } pstmt.executeUpdate(); ResultSet
	 * rs = pstmt.getGeneratedKeys(); if (rs.next()) { return rs.getInt(1); }
	 * else { return -1; } }
	 */

	/*
	 * public List<?> readAll(String query, Object[] vals) throws
	 * ClassNotFoundException, SQLException { int pageNo = getPageNo(); if
	 * (pageNo > -1) { int start = (pageNo - 1) * 10; if (start > 0) { query =
	 * query + " LIMIT " + start + " , " + getPageSize(); } else { query = query
	 * + " LIMIT " + getPageSize(); } }
	 * 
	 * PreparedStatement pstmt = conn.prepareStatement(query);
	 * 
	 * if (vals != null) { int count = 1; for (Object o : vals) {
	 * pstmt.setObject(count, o); count++; } } ResultSet rs =
	 * pstmt.executeQuery(); return (List<T>) extractData(rs); }
	 */

	/*
	 * public int getCount(String query, Object[] vals) throws
	 * ClassNotFoundException, SQLException { PreparedStatement pstmt =
	 * conn.prepareStatement(query);
	 * 
	 * ResultSet rs = pstmt.executeQuery(); if (rs.next()) { return
	 * rs.getInt("count"); } return -1; }
	 */

	// abstract public List<?> extractData(ResultSet rs);

	/*
	 * public List<?> readFirstLevel(String query, Object[] vals) throws
	 * ClassNotFoundException, SQLException { PreparedStatement pstmt =
	 * conn.prepareStatement(query);
	 * 
	 * if (vals != null) { int count = 1; for (Object o : vals) {
	 * pstmt.setObject(count, o); count++; } } ResultSet rs =
	 * pstmt.executeQuery(); return (List<T>) extractDataFirstLevel(rs); }
	 */

	// abstract public List<?> extractDataFirstLevel(ResultSet rs);

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
