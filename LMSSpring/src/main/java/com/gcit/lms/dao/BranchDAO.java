package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BranchDAO extends BaseDAO<Branch>implements ResultSetExtractor<List<Branch>> {
	@Autowired
	BookDAO bdao;
	@Autowired
	BookLoanDAO bldao;

	/*
	 * public BranchDAO(Connection conn) throws Exception { super(conn); // TODO
	 * Auto-generated constructor stub }
	 */

	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_branch where branchId=?", new Object[] { branch.getBranchId() });
	}

	public void create(Branch libraryBranch) throws Exception {
		// template.update the book and get it's id
		int branchId = template.update("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] { libraryBranch.getBranchName(), libraryBranch.getBranchAddress() });

		// if the book wasn't template.updated
		if (branchId == -1) {
			throw new Exception("creating branch failed");
		}

		// // insert into book authors table
		// for (Book b : libraryBranch.getBooks()) {
		// template.update("insert into tbl_book_authors (bookId, authorId)
		// values (?,?)",
		// new Object[] { branchId, b.getAuthorId() });
		// }
		//
		// // insert into book genres table
		// for (Genre g : libraryBranch.getGenres()) {
		// template.update("insert into tbl_book_genres (bookId, genre_id)
		// values (?,?)",
		// new Object[] { branchId, g.getGenreId() });
		// }
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_library_branch", Integer.class);
	}

	@SuppressWarnings("unchecked")
	public List<Branch> readAll() throws Exception {
		return (List<Branch>) template.query("select * from tbl_library_branch", this);

	}

	@SuppressWarnings("unchecked")
	public List<Branch> getAllBranch(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		List<Branch> branchs = (List<Branch>) template.query("select * from tbl_library_branch LIMIT ? OFFSET ?",
				new Object[] { pageSize, (pageNo - 1) * pageSize }, this);
		return branchs;
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException {
		template.update("update tbl_library_branch set branchName = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchId() });
	}

	public void update(Branch libraryBranch) throws Exception {
		template.update("update tbl_library_branch set branchName = ?, branchAddress=? where branchId = ?",
				new Object[] { libraryBranch.getBranchName(), libraryBranch.getBranchAddress(),
						libraryBranch.getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<Branch> getBranchsByName(String searchString) throws ClassNotFoundException, SQLException {
		searchString = "%" + searchString + "%";

		return (List<Branch>) template.query("select * from tbl_library_branch where branchName like ?",
				new Object[] { searchString }, this);

	}

	public void delete(Branch libraryBranch) throws Exception {
		template.update("delete from tbl_library_branch where branchId = ?",
				new Object[] { libraryBranch.getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public Branch getBranchById(Branch branch) throws ClassNotFoundException, SQLException {
		List<Branch> branchs = new ArrayList<Branch>();
		branchs = (List<Branch>) template.query("select * from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() }, this);

		if (branchs != null && branchs.size() > 0) {
			return branchs.get(0);
		}
		return null;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Branch> template.query()
	 * throws Exception { return (List<Branch>) template.query(
	 * "select * from tbl_library_branch", this); }
	 */

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {

		// create a list
		List<Branch> branch = new ArrayList<Branch>();

		// populate the list
		while (rs.next()) {
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			b.setLoans(template.query("select * from tbl_book_loans where branchId=?",
					new Object[] { rs.getInt("branchId") }, bldao));
			branch.add(b);
		}
		return branch;
	}

	public Branch readOne(int branchId) throws Exception {

		List<Branch> branch = template.query("select * from tbl_library_branch where branchId=?",
				new Object[] { branchId }, this);
		if (branch != null && branch.size() > 0) {
			return branch.get(0);
		}
		return null;
	}

	/*
	 * @Override public List<Branch> extractDataFirstLevel(ResultSet rs) {
	 * List<Branch> branches = new ArrayList<Branch>(); // PublisherDAO pdao =
	 * new PublisherDAO(getConnection()); // AuthorDAO aDao = new
	 * AuthorDAO(getConnection()); // GenreDAO gD try { while (rs.next()) {
	 * Branch lb = new Branch(); lb.setBranchId(rs.getInt("branchId"));
	 * lb.setBranchName(rs.getString("branchName"));
	 * lb.setBranchAddress(rs.getString("branchAddress")); branches.add(lb); } }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return branches; }
	 */

}