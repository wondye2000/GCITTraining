package com.gcit.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.dababase.JDBC;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/editAuthor", "/deleteAuthor", "/addBook", "/editBook", "/deleteBook", "/addPublisher",
		"/editPublisher", "/deletePublisher", "/addGenre", "/editGenre", "/deleteGenre", "/addBorrower",
		"/editBorrower", "/deleteBorrower", "/addBranch", "/editBranch", "/deleteBranch" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		// super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());

		switch (reqUrl) {
		case "/deleteAuthor":
			deleteAuthor(request, response);
			break;

		case "/deleteBook":
			deleteBook(request, response);
			break;

		case "/deletePublisher":
			deletePublisher(request, response);
			break;

		case "/deleteGenre":
			deleteGenre(request, response);
			break;

		case "/deleteBorrower":
			deleteBorrower(request, response);
			break;

		case "/deleteBranch":
			deleteBranch(request, response);
			break;
		}

		// RequestDispatcher rd =
		// request.getServletContext().getRequestDispatcher("/author.jsp");
		// rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		// String forwardPath = "admin.jsp";

		switch (reqUrl) {
		case "/editAuthor":
			editAuthor(request, response);
			break;
		// case "/deleteAuthor":
		// deleteAuthor(request);
		// break;

		case "/addAuthor":
			addAuthor(request, response);
			break;

		case "/addBook":
			addBook(request, response);
			break;

		case "/editBook":
			editBook(request, response);
			break;

		case "/addPublisher":
			addPublisher(request, response);
			break;

		case "/editPublisher":
			editPublisher(request, response);
			break;

		case "/addGenre":
			addGenre(request, response);
			break;

		case "/editGenre":
			editGenre(request, response);
			break;

		case "/addBorrower":
			addBorrower(request, response);
			break;

		case "/editBorrower":
			editBorrower(request, response);
			break;

		case "/addBranch":
			addBranch(request, response);
			break;

		case "/editBranch":
			editBranch(request, response);
			break;

		default:
			break;
		}

		// RequestDispatcher rd =
		// request.getServletContext().getRequestDispatcher("/author.jsp");
		// rd.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response) {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);

		JDBC jdbc = new JDBC();
		jdbc.addAuthor(author);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addBorrower(HttpServletRequest request, HttpServletResponse response) {
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");

		Borrower borrower = new Borrower();
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);

		JDBC jdbc = new JDBC();
		jdbc.addBorrower(borrower);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBorrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addPublisher(HttpServletRequest request, HttpServletResponse response) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");

		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);

		JDBC jdbc = new JDBC();
		jdbc.addPublisher(publisher);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewPublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addBranch(HttpServletRequest request, HttpServletResponse response) {
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");

		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);

		JDBC jdbc = new JDBC();
		jdbc.addBranch(branch);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addGenre(HttpServletRequest request, HttpServletResponse response) {
		String genreName = request.getParameter("genreName");

		Genre genre = new Genre();
		genre.setGenreName(genreName);

		JDBC jdbc = new JDBC();
		jdbc.addGenre(genre);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewGenre.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editAuthor(HttpServletRequest request, HttpServletResponse response) {
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");

		List<Book> books = new ArrayList<Book>();
		String[] bookIds = request.getParameterValues("bookId");
		if(bookIds!=null){
		for (String s : bookIds) {
			int bookId = Integer.parseInt(s);
			Book b = new Book();
			b.setBookId(bookId);
			books.add(b);}
		}

		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		author.setBooks(books);

		JDBC jdbc = new JDBC();
		jdbc.editAuthor(author);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editGenre(HttpServletRequest request, HttpServletResponse response) {
		int genreId = Integer.parseInt(request.getParameter("genreId"));
		String genreName = request.getParameter("genreName");

		System.out.println(genreName);
		/*
		 * List<Book> books = new ArrayList<Book>(); String[] bookIds =
		 * request.getParameterValues("bookId"); for (String s : bookIds) { int
		 * bookId = Integer.parseInt(s); Book b = new Book();
		 * b.setBookId(bookId); books.add(b); }
		 */

		Genre genre = new Genre();
		genre.setGenreId(genreId);
		genre.setGenreName(genreName);

		JDBC jdbc = new JDBC();
		jdbc.editGenre(genre);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewGenre.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editPublisher(HttpServletRequest request, HttpServletResponse response) {
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");

		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);

		JDBC jdbc = new JDBC();
		jdbc.editPublisher(publisher);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewPublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBranch(HttpServletRequest request, HttpServletResponse response) {
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");

		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);

		JDBC jdbc = new JDBC();
		jdbc.editBranch(branch);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.html");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBorrower(HttpServletRequest request, HttpServletResponse response) {
		int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");

		Borrower borrower = new Borrower();
		borrower.setCardNo(borrowerId);
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);

		JDBC jdbc = new JDBC();
		jdbc.editBorrower(borrower);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBorrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) {

		int authorId = Integer.parseInt(request.getParameter("authorId"));

		Author author = new Author();
		author.setAuthorId(authorId);

		JDBC jdbc = new JDBC();
		jdbc.deleteAuthor(author);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewauthor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteGenre(HttpServletRequest request, HttpServletResponse response) {

		int genreId = Integer.parseInt(request.getParameter("genreId"));

		Genre genre = new Genre();
		genre.setGenreId(genreId);

		JDBC jdbc = new JDBC();
		jdbc.deleteGenre(genre);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewGenre.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deletePublisher(HttpServletRequest request, HttpServletResponse response) {

		int publisherId = Integer.parseInt(request.getParameter("publisherId"));

		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);

		JDBC jdbc = new JDBC();
		jdbc.deletePublisher(publisher);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewPublisher.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteBorrower(HttpServletRequest request, HttpServletResponse response) {

		int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));

		Borrower borrower = new Borrower();
		borrower.setCardNo(borrowerId);

		JDBC jdbc = new JDBC();
		jdbc.deleteBorrower(borrower);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBorrower.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteBranch(HttpServletRequest request, HttpServletResponse response) {

		int branchId = Integer.parseInt(request.getParameter("branchId"));

		Branch branch = new Branch();
		branch.setBranchId(branchId);

		JDBC jdbc = new JDBC();
		jdbc.deleteBranch(branch);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBranch.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		// String authorName = request.getParameter("authorName");

		Book book = new Book();
		book.setBookId(bookId);
		// author.setAuthorName(authorName);

		JDBC jdbc = new JDBC();
		jdbc.deleteBook(book);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");

		// assuming we choose publisher
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);

		List<Author> authors = new ArrayList<Author>();
		String[] authorIds = request.getParameterValues("authorId");
		if (authorIds != null) {
			for (String s : authorIds) {
				int authorId = Integer.parseInt(s);
				Author a = new Author();
				a.setAuthorId(authorId);
				authors.add(a);
			}

		}

		List<Genre> genres = new ArrayList<Genre>();
		String[] genreIds = request.getParameterValues("genreId");
		if (genreIds != null) {
			for (String s : genreIds) {
				int genreId = Integer.parseInt(s);
				Genre g = new Genre();
				g.setGenreId(genreId);
				genres.add(g);
			}

		}

		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenres(genres);

		book.setAuthors(authors);

		JDBC jdbc = new JDBC();
		jdbc.addBook(book);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");

		// assuming we choose publisher
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);

		List<Author> authors = new ArrayList<Author>();
		String[] authorIds = request.getParameterValues("authorId");

		if (authorIds != null) {
			for (String s : authorIds) {
				int authorId = Integer.parseInt(s);
				Author a = new Author();
				a.setAuthorId(authorId);
				authors.add(a);
			}
		}

		List<Genre> genres = new ArrayList<Genre>();
		String[] genreIds = request.getParameterValues("genreId");
		if (genreIds != null) {
			for (String s : genreIds) {
				int genreId = Integer.parseInt(s);
				Genre g = new Genre();
				g.setGenreId(genreId);
				genres.add(g);

			}
		}

		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(title);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenres(genres);

		book.setAuthors(authors);

		JDBC jdbc = new JDBC();
		jdbc.editBook(book);

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/viewBook.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
