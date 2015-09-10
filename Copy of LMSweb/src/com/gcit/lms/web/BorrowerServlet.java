package com.gcit.lms.web;

import java.io.IOException;

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

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/returnBook","/checkOutBook"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
      //  super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath="borrower.jsp";
		switch (reqUrl) {
		case "/returnBook":
			//addAuthor(request);
			//request.setAttribute("addAuthorMessage", "Author Added Sucessfully");
			//forwardPath = "/author.jsp";
			break;
		case "/checkOutBook":
			//System.out.println(request.getParameter("authorId"));
			break;

		default:
			break;
		}
	
    RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/author.jsp");
	rd.forward(request, response);
	}

	private void add(HttpServletRequest request) {
		String authorName=request.getParameter("authorName");
		Author author=new Author();
		author.setAuthorName(authorName);
		
		JDBC jdbc=new JDBC();
		jdbc.addAuthor(author);
	}
	private void checkOut(HttpServletRequest request) {
		String bookTitle=request.getParameter("bookTitle");
		Book book=new Book();
		book.setTitle(bookTitle);
		
		JDBC jdbc=new JDBC();
		jdbc.checkedOut(book);
	}


private void returnBook(HttpServletRequest request) {
	String authorName=request.getParameter("authorName");
	Author author=new Author();
	author.setAuthorName(authorName);
	
	JDBC jdbc=new JDBC();
	jdbc.addAuthor(author);
}

}

