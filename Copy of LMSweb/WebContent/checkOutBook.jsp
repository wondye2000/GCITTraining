<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.dababase.JDBC" %>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
JDBC jdbc = new JDBC();
List<LibraryBranch> lib=new ArrayList<LibraryBranch>();
lib=jdbc.getBranch();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to GCIT LMS</title>
</head>
<body>
<h1>Please select Branch</h1>
<form action="borrower1.jsp" method="post">
<select required>
<%for(LibraryBranch a:lib){%>
<option value="volvo"><%=a.getBranchName() %></option>
<%} %>
  </select>
  <input type="submit">
  </form>
</body>
</html>