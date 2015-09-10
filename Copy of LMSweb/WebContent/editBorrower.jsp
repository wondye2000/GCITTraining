<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	//AdministrativeService adminService = new AdministrativeService();

	JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();

	int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
	Borrower borrower = jdbc.getBorrower(borrowerId);
%>
<div class="modal-body">
	<form action="editBorrower" method="post">
		<div>
			Enter Borrower Name: <input type="text" name="borrowerName"
				value=<%=borrower.getName()%>> <input
				type="hidden" name="publisherId" value=<%=borrowerId%>> <input
				type="submit" />
		</div>

		<div>
			Enter Borrower Address: <input type="text" name="borrowerAddress"
				value=<%=borrower.getAddress()%>> <input
				type="hidden" name="borrowerId" value=<%=borrowerId%>>
		</div>

		<div>
			Enter Borrower Phone: <input type="text" name="borrowerPhone"
				value=<%=borrower.getPhone()%>> <input
				type="hidden" name="borrowerId" value=<%=borrowerId%>>
		</div>



	</form>
</div>