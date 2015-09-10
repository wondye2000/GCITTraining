<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.gcit.lms.dababase.JDBC" %>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
JDBC jdbc = new JDBC();
List<Book> bk=new ArrayList<Book>();
bk=jdbc.getTitle();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Please Select the Book you want to Checkout/Return </h1>
<<table><tr><th>Book ID</th><th>Title</th><th>CheckOut</th><th>Return Book</th></tr><%for(Book a:bk){%>
<tr>
<td><%=a.getBookId() %></td>
<td><%=a.getTitle() %></td>
<td><button type="button" onclick="javascript:location.href='checkOutBook?bookId=<%=a.getBookId()%>'">CheckOut</button></td>
<td><button type="button"onclick="javascript:location.href='returnBook?authorId=<%=a.getTitle()%>'">Return</button></td>
</tr>
<%} %>
</table>
</body>
</html>