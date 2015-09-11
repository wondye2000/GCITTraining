package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	Connection conn = null;
	
	public BaseDAO(Connection conn){
		this.conn = conn;
	}
	
	public void save(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public int saveWithID(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return -1;
		}
	}
	
	public List<?> readAll(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return (List<T>) extractData(rs);
	}
	
	abstract public List<?> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;
	
	
	public List<?> readFirstLevel(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count =1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return (List<T>) extractDataFirstLevel(rs);
	}
	
	abstract public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
