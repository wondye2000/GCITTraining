<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Borrower> borrower = new ArrayList<Borrower>();
	borrower = jdbc.getBorrowers();
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
<body>
	<h1>View Existing Borrowers</h1>
	<table style="width:100%">
		<tr>
			<th>Card No.</th>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone</th>
			<th>Edit Borrower</th>
			<th>Delete Borrower</th>
		</tr>
		<%
			for (Borrower b : borrower) {
		%>
		<tr>
			<td><%=b.getCardNo()%></td>
			<td><%=b.getName()%></td>
			<td><%=b.getAddress()%></td>
			<td><%=b.getPhone()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editBorrower.jsp?borrowerId=<%=b.getCardNo()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBorrower?borrowerId=<%=b.getCardNo()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>