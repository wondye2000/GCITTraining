/*package com.gcit.lms.dababase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;

public class JDBC {

	public void addAuthor(Author author) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("insert into tbl_author (authorName) values (?)");
			pstmt.setString(1, author.getAuthorName());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBookCopies(BookCopies bookCopy) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_copies values (?, ?, ?)");
			pstmt.setInt(1, bookCopy.getBookId());
			pstmt.setInt(2, bookCopy.getBranchId());
			pstmt.setInt(3, bookCopy.getNoOfCopies());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBorrower(Borrower borrower) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("insert into tbl_borrower (name, address, phone) values (?, ?, ?)");
			pstmt.setString(1, borrower.getName());
			pstmt.setString(2, borrower.getAddress());
			pstmt.setString(3, borrower.getPhone());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addPublisher(Publisher publisher) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					"insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)");
			pstmt.setString(1, publisher.getPublisherName());
			pstmt.setString(2, publisher.getPublisherAddress());
			pstmt.setString(3, publisher.getPublisherPhone());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBranch(Branch branch) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("insert into tbl_library_branch (branchName, branchAddress) values (?, ?)");
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addGenre(Genre genre) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_genre (genre_name) values (?)");
			pstmt.setString(1, genre.getGenreName());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editAuthor(Author author) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("update tbl_author set authorName = ? where authorId = ?");
			pstmt.setString(1, author.getAuthorName());
			pstmt.setInt(2, author.getAuthorId());
			pstmt.executeUpdate();

			pstmt = getConnection().prepareStatement("delete from tbl_book_authors where authorId = ?");
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();

			pstmt = getConnection().prepareStatement("insert into tbl_book_authors (bookId, authorId) values (?, ?)");
			for (Book b : author.getBooks()) {
				pstmt.setInt(1, b.getBookId());
				pstmt.setInt(2, author.getAuthorId());
				pstmt.executeUpdate();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editBookCopies(BookCopies bookCopy) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?");
			pstmt.setInt(1, bookCopy.getNoOfCopies());
			pstmt.setInt(2, bookCopy.getBookId());
			pstmt.setInt(3, bookCopy.getBranchId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editGenre(Genre genre) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("update tbl_genre set genre_name = ? where genre_id = ?");
			pstmt.setString(1, genre.getGenreName());
			pstmt.setInt(2, genre.getGenreId());
			pstmt.executeUpdate();

			// pstmt = getConnection().prepareStatement("delete from
			// tbl_book_genres where genre_id = ?");
			// pstmt.setInt(1, genre.getGenreId());
			// pstmt.executeUpdate();
			//
			// pstmt = getConnection().prepareStatement("insert into
			// tbl_book_genres (bookId, genre_id) values (?, ?)");
			// for (Book b : genre.getBooks()) {
			// pstmt.setInt(1, b.getBookId());
			// pstmt.setInt(2, genre.getGenreId());
			// pstmt.executeUpdate();
			// }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editPublisher(Publisher publisher) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					"update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?");
			pstmt.setString(1, publisher.getPublisherName());
			pstmt.setString(2, publisher.getPublisherAddress());
			pstmt.setString(3, publisher.getPublisherPhone());
			pstmt.setInt(4, publisher.getPublisherId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editBranch(Branch branch) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					"update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?");
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());
			pstmt.setInt(3, branch.getBranchId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editBorrower(Borrower borrower) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("update tbl_borrower set name = ?, address = ?, Phone = ? where cardNo = ?");
			pstmt.setString(1, borrower.getName());
			pstmt.setString(2, borrower.getAddress());
			pstmt.setString(3, borrower.getPhone());
			pstmt.setInt(4, borrower.getCardNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAuthor(Author author) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_author where authorId = ?");
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGenre(Genre genre) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_genre where genre_Id = ?");
			pstmt.setInt(1, genre.getGenreId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePublisher(Publisher publisher) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("delete from tbl_publisher where publisherId = ?");
			pstmt.setInt(1, publisher.getPublisherId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBorrower(Borrower borrower) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_borrower where cardNo = ?");
			pstmt.setInt(1, borrower.getCardNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBranch(Branch branch) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("delete from tbl_library_branch where branchId = ?");
			pstmt.setInt(1, branch.getBranchId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(Book book) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book where bookId = ?");
			pstmt.setInt(1, book.getBookId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Author> getAuthors() {
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_author");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));

				authors.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}

	public List<Book> getBooksFromBranch(Branch branch) {
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("select * from (tbl_book natural join tbl_book_copies) where branchId = ?");
			pstmt.setInt(1, branch.getBranchId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));

				books.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<Genre> getGenres() {
		List<Genre> genre = new ArrayList<Genre>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_genre");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_id"));
				g.setGenreName(rs.getString("genre_name"));

				genre.add(g);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));

				books.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public Book getBook(int bookId) {
		Book book = new Book();
		book.setBookId(bookId);
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book where bookId = ?");
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();

			// while (rs.next()) {
			// Book b = new Book();
			rs.next();

			book.setTitle(rs.getString("title"));

			// book.add(b);
			// }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public Author getAuthor(int authorId) {
		Author author = new Author();
		author.setAuthorId(authorId);
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_author where authorId = ?");
			pstmt.setInt(1, authorId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			author.setAuthorName(rs.getString("authorName"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}

	public int getNoOfCopies(int bookId, int branchId) {
		int noOfCopies = 0;
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("select * from tbl_book_copies where bookId = ? and branchId = ?");
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, branchId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				noOfCopies = rs.getInt("noOfCopies");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfCopies;
	}

	public Borrower getBorrower(int borrowerId) {
		Borrower borrower = new Borrower();
		borrower.setCardNo(borrowerId);
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower where cardNo = ?");
			pstmt.setInt(1, borrowerId);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			borrower.setName(rs.getString("Name"));
			borrower.setAddress(rs.getString("Address"));
			borrower.setPhone(rs.getString("Phone"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}

	public List<Borrower> getBorrowers() {
		List<Borrower> borrower = new ArrayList<Borrower>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Borrower b = new Borrower();
				b.setCardNo(rs.getInt("cardNo"));
				b.setName(rs.getString("Name"));
				b.setAddress(rs.getString("Address"));
				b.setPhone(rs.getString("Phone"));

				borrower.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}

	public Publisher getPublisher(int publisherId) {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("select * from tbl_publisher where publisherId = ?");
			pstmt.setInt(1, publisherId);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}

	public Branch getBranch(int branchId) {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("select * from tbl_Library_branch where branchId = ?");
			pstmt.setInt(1, branchId);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}

	public List<Publisher> getPublishers() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_publisher");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setPublisherName(rs.getString("publisherName"));
				p.setPublisherAddress(rs.getString("publisherAddress"));
				p.setPublisherPhone(rs.getString("publisherPhone"));

				publishers.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publishers;
	}

	public List<Branch> getBranchs() {
		List<Branch> branch = new ArrayList<Branch>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_library_branch");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Branch b = new Branch();
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setBranchAddress(rs.getString("branchAddress"));

				branch.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}

	public Genre getGenre(int genreId) {
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_genre where genre_id = ?");
			pstmt.setInt(1, genreId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			genre.setGenreName(rs.getString("genre_name"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}

	public List<LibraryBranch> getBranch() {
		List<LibraryBranch> br = new ArrayList<LibraryBranch>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_Library_branch");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				LibraryBranch a = new LibraryBranch();
				a.setBranchId(rs.getInt("branchId"));
				a.setBranchName(rs.getString("branchName"));

				br.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}

	public void addBook(Book book) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					"insert into tbl_book (title, pubId) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getPublisher().getPublisherId());
			pstmt.executeUpdate();

			// tbl_book_authors table

			ResultSet rs = pstmt.getGeneratedKeys();

			rs.next();
			int bookId = rs.getInt(1);

			pstmt = getConnection().prepareStatement("insert into tbl_book_authors (bookId, authorId) values (?, ?)");
			for (Author a : book.getAuthors()) {
				pstmt.setInt(1, bookId);
				pstmt.setInt(2, a.getAuthorId());
				pstmt.executeUpdate();
			}

			pstmt = getConnection().prepareStatement("insert into tbl_book_genres (genre_id, bookId) values (?, ?)");
			for (Genre g : book.getGenres()) {
				pstmt.setInt(1, g.getGenreId());
				pstmt.setInt(2, bookId);
				pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editBook(Book book) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("update tbl_book set title = ?, pubId = ? where bookId = ?");
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getPublisher().getPublisherId());
			pstmt.setInt(3, book.getBookId());

			pstmt.executeUpdate();

			pstmt = getConnection().prepareStatement("delete from tbl_book_authors where bookId = ?");
			pstmt.setInt(1, book.getBookId());
			pstmt.executeUpdate();

			pstmt = getConnection().prepareStatement("insert into tbl_book_authors (bookId, authorId) values (?, ?)");
			for (Author a : book.getAuthors()) {
				pstmt.setInt(1, book.getBookId());
				pstmt.setInt(2, a.getAuthorId());
				pstmt.executeUpdate();
			}

			pstmt = getConnection().prepareStatement("delete from tbl_book_genres where bookId = ?");
			pstmt.setInt(1, book.getBookId());
			pstmt.executeUpdate();

			pstmt = getConnection().prepareStatement("insert into tbl_book_genres (genre_id, bookId) values (?, ?)");
			for (Genre g : book.getGenres()) {
				pstmt.setInt(1, g.getGenreId());
				pstmt.setInt(2, book.getBookId());
				pstmt.executeUpdate();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getTitle() {
		List<Book> br = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Book a = new Book();
				a.setBookId(rs.getInt("bookId"));
				a.setTitle(rs.getString("title"));

				br.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}

	public void checkedOut(Book bk) {
		try {
			PreparedStatement pstmt = getConnection()
					.prepareStatement("insert into tbl_book_loans (bookID) values (?)");
			pstmt.setLong(1, bk.getBookId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	//private Connection getConnection() throws ClassNotFoundException, SQLException {
		//Class.forName("com.mysql.jdbc.Driver");
		//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
		///return conn;
	//}/
//}
