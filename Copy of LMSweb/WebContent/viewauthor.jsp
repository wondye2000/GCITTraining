<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Author> authors = new ArrayList<Author>();
	authors = jdbc.getAuthors();
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
	<h1>View Existing Authors</h1>
	<table style="width:100%">
		<tr>
			<th>Author ID</th>
			<th>Author Name</th>
			<th>Edit Author</th>
			<th>Delete Author</th>
		</tr>
		<%
			for (Author a : authors) {
		%>
		<tr>
			<td><%=a.getAuthorId()%></td>
			<td><%=a.getAuthorName()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editAuthor.jsp?authorId=<%=a.getAuthorId()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>