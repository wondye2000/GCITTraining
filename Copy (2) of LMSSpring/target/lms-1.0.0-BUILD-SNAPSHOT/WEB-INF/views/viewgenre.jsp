<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Genre"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	//AdministratorService service = new AdministratorService();
	//int count = service.getGenresCount();
	int pages = (Integer) request.getAttribute("pages");

	//if (count % 10 == 0) {
	//	pages = count / 10;
	//} else {
	//	pages = (count / 10) + 1;
	//}

	List<Genre> genres = new ArrayList<Genre>();
	if (request.getAttribute("genres") != null) {
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		//genres = service.getGenres(1, 10);
	}
%>
<script>
	function searchGenres() {
		$.ajax({
			url : "searchGenres",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#genresTable').html(data);
		});
	}
</script>
<style>
table, th, td {
	border: 0px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 {
	width: 100%;
	background-color: #f1f1c1;
}
</style>

<form action="searchGenres" method="post">
	<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search"> <input type="submit"
		value="Search!" onclick="javascript:searchGenres();">
</form>
<h2>View Existing Genres</h2>
<nav>
	<ul class="pagination">
		<%
			for (int i = 1; i <= pages; i++) {
		%>
		<li><a href="pageGenres?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>
	</ul>
</nav>
<table class="table" id="genresTable" style="width: 100%">
	<tr>
		<th>Genre ID</th>
		<th>Genre Name</th>
		<th>Edit Genre</th>
		<th>Delete Genre</th>
	</tr>
	<%
		for (Genre g : genres) {
	%>
	<tr>
		<td align="center"><%=g.getGenreId()%></td>
		<td align="center"><%=g.getGenreName()%></td>
		<td align="center"><button type="button"
				class="btn btn btn-primary" data-toggle="modal"
				data-target="#myModal1"
				href="editgenre.jsp?genreId=<%=g.getGenreId()%>">EDIT</button></td>
		form action="deleteGenre" method="post">
		<td align="center"><input type="hidden" name="genreId"
			value=<%=g.getGenreId()%>>
			<button type="submit" class="btn btn btn-danger"
				>DELETE</button></td>

		</form>
	</tr>
	<%
		}
	%>

</table>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>
