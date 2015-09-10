<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Genre> genre = new ArrayList<Genre>();
	genre = jdbc.getGenres();
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
	<h1>View Existing Genre</h1>
	<table>
		<tr>
			<th>Genre ID</th>
			<th>Genre Name</th>
			<th>Edit Genre</th>
			<th>Delete Genre</th>
		</tr>
		<%
			for (Genre g : genre) {
		%>
		<tr>
			<td><%=g.getGenreId()%></td>
			<td><%=g.getGenreName()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editGenre.jsp?genreId=<%=g.getGenreId()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteGenre?genreId=<%=g.getGenreId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>