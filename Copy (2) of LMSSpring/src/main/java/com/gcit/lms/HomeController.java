package com.gcit.lms;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
//import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
//import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.BookLoans;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;

@RestController
public class HomeController {

	@Autowired
	AdministratorService adminSerrvice;

	@Autowired
	AuthorDAO adao;
	@Autowired
	BookDAO bdao;
	@Autowired
	PublisherDAO pdao;
	@Autowired
	BorrowerDAO bodao;
	@Autowired
	BranchDAO brdao;
	@Autowired
	GenreDAO gdao;
	@Autowired
	BookLoanDAO bldao;
	@Autowired
	BookCopiesDAO bcdao;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@Transactional
	@RequestMapping(value = "/addAuthor", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addAuthor(@RequestBody Author author) {
		try {
			adao.createAuthor(author);System.out.println(author);
			return "Author Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Author Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addBook", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addBook(@RequestBody Book book) {
		try {
			bdao.createBook(book);
			return "Book Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addPublisher", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addPublisher(@RequestBody Publisher publisher) {
		try {
			pdao.createPublisher(publisher);
			return "Publisher Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Publisher Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addBorrower", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addBorrower(@RequestBody Borrower borrower) {
		try {
			bodao.create(borrower);
			return "Borrower Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Borrower Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addBranch", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addBranch(@RequestBody Branch branch) {
		try {
			brdao.create(branch);
			return "Library Branch Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Library Branch Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addGenre", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addGenre(@RequestBody Genre genre) {
		try {
			gdao.createGenre(genre);
			return "Genre Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Genre Add Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/addBookLoans", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String addBookLoans(@RequestBody BookLoans loan) {
		try {
			bldao.createBookLoan(loan);
			return "Book Loan Added Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Loan Add Failed.";
		}
	}

	@RequestMapping(value = "/getAuthors", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Author> getAuthors() {
		try {
			return adao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getAuthors/{pageNo}/{pageSize}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Author> getAuthors(@PathVariable int pageNo, @PathVariable int pageSize) {
		try {
			return adao.getAllAuthors(pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBooks", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Book> getBooks() {
		try {
			return bdao.readAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBranchBooks/{branchID}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Book> getBranchBooks(@PathVariable int branchID) {
		try {
			return bdao.branchBooks(branchID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getPublishers", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Publisher> getPublishers() {
		try {
			return pdao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBorrowers", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Borrower> getBorrowers() {
		try {
			return bodao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBranches", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Branch> getBranches() {
		try {
			return brdao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getGenres", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<Genre> getGenres() {
		try {
			return gdao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBookLoans", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<BookLoans> getBookLoans() {
		try {
			return bldao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBookCopies", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public List<BookCopies> getBookCopies() {
		try {
			return bcdao.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/editAuthor", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editAuthor(@RequestBody Author author) {
		try {
			adao.updateAuthor(author);
			return "Author Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Author Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editBook", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editBook(@RequestBody Book book) {
		try {
			bdao.updateBook(book);
			return "Book Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editBorrower", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editBorrower(@RequestBody Borrower borrower) {
		try {
			bodao.update(borrower);
			return "Borrower Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Borrower Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editPublisher", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editPublisher(@RequestBody Publisher publisher) {
		try {
			pdao.updatePublisher(publisher);
			return "Book Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editGenre", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editGenre(@RequestBody Genre genre) {
		try {
			gdao.updateGenre(genre);
			return "Genre Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Genre Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editBranch", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editBranch(@RequestBody Branch branch) {
		try {
			brdao.updateBranch(branch);
			return "Library Branch Updated Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Library Branch Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editBookLoans", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String editBookLoans(@RequestBody BookLoans loan) {
		try {
			bldao.update(loan);
			return "Book Returned Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Return Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/editBookCopies", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public String editBookCopies(@RequestBody BookCopies copy) {
		try {
			bcdao.updateBookCopies(copy);
			return "Number of Copies Updated.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Number of Copies Update Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteAuthor", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteAuthor(@RequestBody Author author) {
		try {
			adao.deleteAuthor(author);
			return "Author Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Author Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBook", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteBook(@RequestBody Book book) {
		try {
			bdao.deleteBook(book);
			return "Book Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBorrower", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteBorrower(@RequestBody Borrower borrow) {
		try {
			bodao.delete(borrow);
			return "Borrower Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Borrower Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deletePublisher", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deletePublisher(@RequestBody Publisher publisher) {
		try {
			pdao.deletePublisher(publisher);
			return "Publisher Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Publisher Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteGenre", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteGenre(@RequestBody Genre genre) {
		try {
			gdao.deleteGenre(genre);
			return "Genre Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Genre Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBranch", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteBranch(@RequestBody Branch branch) {
		try {
			brdao.deleteBranch(branch);
			return "Library Branch Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Library Branch Delete Failed.";
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBookLoans", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = "application/json")
	public String deleteBookLoans(@RequestBody BookLoans loan) {
		try {
			bldao.delete(loan);
			return "Book Loan Record Deleted Sucessfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Book Loan Record Delete Failed.";
		}
	}

	@RequestMapping(value = "/getAuthorCount", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public String getAuthorCount() {
		try {
			String result = "Total Authors in DB: " + adao.getCount();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

}