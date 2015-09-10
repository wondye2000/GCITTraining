<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	//AdministrativeService adminService = new AdministrativeService();

	JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();

	int genreId = Integer.parseInt(request.getParameter("genreId"));
	Genre genre = jdbc.getGenre(genreId);
%>
<form action="editGenre" method="post">
	<div>
		Enter Genre Name: <input type="text" name="genreName"
			value=<%=genre.getGenreName()%>> <input type="hidden"
			name="genreId" value=<%=genreId%>> <input type="submit" />
	</div>




</form>
