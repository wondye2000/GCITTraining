<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gcit.lms.service.AdministratorService" %>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
AdministratorService service = new AdministratorService();
List<Branch> lib=new ArrayList<Branch>();
lib=service.getBranch();
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
<%for(Branch a:lib){%>
<option value="volvo"><%=a.getBranchName() %></option>
<%} %>
  </select>
  <input type="submit">
  </form>
</body>
</html>