<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Publisher> publishers = new ArrayList<Publisher>();
	publishers = jdbc.getPublishers();
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
	<h1>View Existing Publishers</h1>
	<table style="width:100%">
		<tr>
			<th>Publisher ID</th>
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Edit Publisher</th>
			<th>Delete Publisher</th>
		</tr>
		<%
			for (Publisher p : publishers) {
		%>
		<tr>
			<td><%=p.getPublisherId()%></td>
			<td><%=p.getPublisherName()%></td>
			<td><%=p.getPublisherAddress()%></td>
			<td><%=p.getPublisherPhone()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editPublisher.jsp?publisherId=<%=p.getPublisherId()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>