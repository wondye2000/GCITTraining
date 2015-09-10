<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	JDBC jdbc = new JDBC();
	List<Branch> branch = new ArrayList<Branch>();
	branch = jdbc.getBranchs();
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
<title>GCIT Library Management System</title>
</head>
<body>
	<h1>View Existing Branches</h1>
	<table>
		<tr>
			<th>Branch ID</th>
			<th>Branch Name</th>
			<th>branch Address</th>
			<th>Edit Branch</th>
			<th>Add Copies</th>
		</tr>
		<%
			for (Branch b : branch) {
		%>
		<tr>
			<td><%=b.getBranchId()%></td>
			<td><%=b.getBranchName()%></td>
			<td><%=b.getBranchAddress()%></td>
			<td><button type="button"
					onclick="javascript:location.href='editBranch.jsp?branchId=<%=b.getBranchId()%>'">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='addBookCopies.jsp?branchId=<%=b.getBranchId()%>'">Add Book copies</button></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>