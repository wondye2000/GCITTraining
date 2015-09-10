<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();
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
<title>Library Management System</title>
</head>
<body>
	<h1>View Existing Books</h1>
	<table style="width:100%">
		<tr>
			<th>Book ID</th>
			<th>Book Title</th>
			<th>Edit Book</th>
			<th>Delete Book</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<td><%=b.getBookId()%></td>
			<td><%=b.getTitle()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editBook.jsp?bookId=<%=b.getBookId()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>