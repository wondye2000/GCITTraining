package com.gcit.lms.service;

import java.sql.Connection;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.domain.Author;

public class AdministratorService {

	ConnectionUtil connUtil = new ConnectionUtil();

	private void createAuthor(Author author) throws Exception {
		//Boolean flag = validateAuthor(author)
			Connection conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			try{
				if(author.getAuthorName()!=null){
				if(author.getAuthorName().length() > 45){
					throw new Exception("Author Name cannot be more than 45 chars");
				}else{
					adao.createAuthor(author);
					}
				}
				conn.commit();
			}catch(Exception e){
				e.printStackTrace();
				conn.rollback();
			}finally{
				conn.close();
			}
	}
}

