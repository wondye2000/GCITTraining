<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<!<%
out.println(request.getAttribute("message"));
%>>
<body>
<h2><a href="addBook.jsp">Add Book</a></h2>
<h2><a href="viewBook.jsp">View Book</a></h2>
</body>
</html>