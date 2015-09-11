package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.gcit.lms.domain.*;

public class BranchDAO extends BaseDAO{

	public BranchDAO(Connection conn) throws Exception {
		super(conn);
	}

	public void update(Branch Branch) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { Branch.getBranchName(), Branch.getBranchAddress(), Branch.getBranchId() });
	}	
	public void delete(LibraryBranch libraryBranch) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { libraryBranch.getBranchId() });
	}

	public void create(LibraryBranch libraryBranch) throws Exception {
		int branchId = saveWithID("insert into tbl_library_branch (branchName, branchAddress) values(?,?)",
				new Object[] { libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
	
	}
	
	public List<LibraryBranch> readAll() throws Exception{
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch", null);
		
	}
	
	public LibraryBranch readOne(int branchId) throws Exception {
		List<LibraryBranch> libraryBranches = (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId = ?",
				new Object[] {branchId});
		if(libraryBranches!=null && libraryBranches.size()>0){
			return libraryBranches.get(0);
		}
		return null;
	}
	
	//TODO: Missing
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) {
		List<LibraryBranch> libraryBranches = new ArrayList<LibraryBranch>();
		BookDAO bdao = new BookDAO(conn);
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		BookLoanDAO bldao = new BookLoanDAO(conn);
		
		while(rs.next()){
			HashMap<Book, Integer> bookCopies = new HashMap<Book, Integer>();
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(rs.getInt("branchId"));
			lb.setBranchName(rs.getString("branchName"));
			lb.setBranchAddress(rs.getString("branchAddress"));
			lb.setLoans(bldao.readFirstLevel("select * from tbl_book_loans where branchId = ?", new Object[] {rs.getInt("branchId")}));
			
			@SuppressWarnings("unchecked")
			List<BookCopies> bcs = (List<BookCopies>) bcdao.readFirstLevel("select * from tbl_book_copies where branchId = ?"
					, new Object[] {rs.getInt("branchId")});
			for(BookCopies bc: bcs){
				bookCopies.put(bdao.readOne(bc.getBookId()), bc.getNoOfCopies());
			}
			lb.setBookCopies(bookCopies);
			libraryBranches.add(lb);
		}
		
		return libraryBranches;
	}
	
	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Branch> branches = new ArrayList<Branch>();
		while(rs.next()){
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(rs.getInt("branchId"));
			lb.setBranchAddress(rs.getString("branchAddress"));
			lb.setBranchName(rs.getString("branchName"));
			branches.addAll(lb);
		}
		return branches;
	}

}
