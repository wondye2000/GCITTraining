
<%@include file="include.html"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	// AdministratorService service = new AdministratorService();
	List<Author> authors = (List<Author>) request.getAttribute("authors");

	// authors = service.getAuthors();

	List<Publisher> publishers = (List<Publisher>) request.getAttribute("publishers");
	// publishers = service.getPublishers();

	List<Genre> genres = (List<Genre>) request.getAttribute("genres");
	// genres = service.getGenres();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT LMS</title>
</head>
<form action="addbook" method="post">

	<div>
		<input type="text" name="title" value="title"><input
			type="submit" value="submit">
	</div>

	<div>
		Select Author: <select name="authorId" multiple="multiple">
			<%
				for (Author a : authors) {
			%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName()%></option>
			<%
				}
			%>
		</select>
	</div>

	<div>
		Select Genre: <select name="genreId" multiple="multiple">
			<%
				for (Genre g : genres) {
			%>
			<option value=<%=g.getGenreId()%>><%=g.getGenreName()%></option>
			<%
				}
			%>

		</select>
	</div>

	<div>
		Select Publisher: <select name="publisherId">
			<%
				for (Publisher p : publishers) {
			%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName()%></option>

			<%
				}
			%>
		</select>
	</div>


</form>