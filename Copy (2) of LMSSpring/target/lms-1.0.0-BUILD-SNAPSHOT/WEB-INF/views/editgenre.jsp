<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%
	AdministratorService adminService = new AdministratorService();
	String genreId = request.getParameter("genreId");
	Genre genre = adminService.getGenreByID(Integer.parseInt(genreId));
%>
<div class="modal-body">
	<form action="editGenre" method="post">
		Enter Genre Name: <input type="text" name="genreName"
			value="<%=genre.getGenreName()%>"> <input type="hidden"
			name="genreId" value=<%=genre.getGenreId()%>> <input
			type="submit" />
	</form>
</div>