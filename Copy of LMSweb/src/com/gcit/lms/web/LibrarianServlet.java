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
import com.gcit.lms.domain.BookCopies;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addBookCopies" })
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
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
		// case "/deleteAuthor":
		// deleteAuthor(request);
		// break;
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

		case "/addBookCopies":
			addBookCopies(request,response);
			break;

		default:
			break;
		}

			}

	private void addBookCopies(HttpServletRequest request,HttpServletResponse response) {

		int branchId = Integer.parseInt(request.getParameter("branchId"));

		String[] addedNoOfCopies = request.getParameterValues("noOfCopies");
		String[] bookIds = request.getParameterValues("bookId");

		JDBC jdbc = new JDBC();

		for (int i = 0; i < addedNoOfCopies.length; i++) {
			if (!addedNoOfCopies[i].equals("")) {
				int oldNoOfCopies = jdbc.getNoOfCopies(Integer.parseInt(bookIds[i]), branchId);

				int newNoOfCopies = oldNoOfCopies + Integer.parseInt(addedNoOfCopies[i]);

				BookCopies bookCopy = new BookCopies(Integer.parseInt(bookIds[i]), branchId, newNoOfCopies);
				if (oldNoOfCopies == 0) {
					jdbc.addBookCopies(bookCopy);
				} else {
					jdbc.editBookCopies(bookCopy);
				}
			}
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/addBookCopies.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
