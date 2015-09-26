<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	//AdministratorService service = new AdministratorService();
	//int count = service.getAuthorsCount();
	int pages = (Integer) request.getAttribute("pages");

	//if (count % 10 == 0) {
	//	pages = count / 10;
	//} else {
	//	pages = (count / 10) + 1;
	//} 

	List<Author> authors = new ArrayList<Author>();
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		//authors = service.getAuthors(1, 10);
	}
%>
<script>
	function searchAuthors() {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#authorsTable').html(data);
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

<form action="searchAuthors" method="post">
	<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search"> <input type="submit"
		value="Search!" onclick="javascript:searchAuthors();">
</form>
<h2>View Existing Authors</h2>
<nav>
	<ul class="pagination">
		<%
			for (int i = 1; i <= pages; i++) {
		%>
		<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>
	</ul>
</nav>
<table class="table" id="authorsTable">
	<tr>
		<th>Author ID</th>
		<th>Author Name</th>
		<th>Edit Author</th>
		<th>Delete Author</th>
	</tr>
	<%
		for (Author a : authors) {
	%>
	<tr>
		<td align="center"><%=a.getAuthorId()%></td>
		<td align="center"><%=a.getAuthorName()%></td>
		<td align="center"><button type="button"
				class="btn btn btn-primary" data-toggle="modal"
				data-target="#myModal1"
				href="editauthor?authorId=<%=a.getAuthorId()%>">EDIT</button></td>

		<form action="deleteAuthor" method="post">
		<td align="center"><input type="hidden" name="authorId"
			value=<%=a.getAuthorId()%>>
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
