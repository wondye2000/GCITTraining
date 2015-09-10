<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	/*List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();*/

	int branchId = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = jdbc.getBranch(branchId);
	List<Book> books = jdbc.getBooks();

	request.setAttribute("branchId", branchId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>

<form action="addBookCopies" method="post">
	<body>
		<h1>View Books in this Branch</h1>
		<table style="width:100%">
			<tr>
				<th>Book ID</th>
				<th>Book Title</th>
				<th>Existing # of Copies</th>
				<th># of added copies</th>
				<input type="submit">
				<!-- <th>Delete Book</th> -->
			</tr>
			<%
				for (Book b : books) {
			%>
			<tr>
				<td><%=b.getBookId()%></td>
				<td><%=b.getTitle()%></td>

				<td><label><%=jdbc.getNoOfCopies(b.getBookId(), branchId)%></label></td>

				<td><input type="text" name="noOfCopies"
					placeholder="new # of copies" /></td>

		<td><input type="hidden" name="bookId" value=<%=b.getBookId()%>><input
					type="hidden" name="branchId" value=<%=branchId%>></td>
			</tr>
			<%
				}
			%>
		</table>

	</body>
</form>
</html>