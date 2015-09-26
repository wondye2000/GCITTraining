<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%
	//AdministratorService service = new AdministratorService();
	//int count = service.getAuthorsCount();
	int pages = (Integer) request.getAttribute("pages");

	//if (count % 10 == 0) {
	//	pages = count / 10;
	//	} else {
	//	pages = (count / 10) + 1;
	//} 
	List<Book> books = new ArrayList<Book>();
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		//books = service.getBooks(1, 10);
	}
%>
<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#booksTable').html(data);
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
<form action="searchBooks" method="post">
	<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search"> <input type="submit"
		value="Search!" onclick="javascript:searchBooks();">
</form>
<h2>View Existing Books</h2>
<nav>
	<ul class="pagination">
		<%
			for (int i = 1; i <= pages; i++) {
		%>
		<li><a href="pageBooks?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>
	</ul>
</nav>
<table class="table" id="booksTable">
	<tr>
		<th>Book ID</th>
		<th>Book Title</th>
		<th>Edit Book</th>
		<th>Delete Book</th>
	</tr>
	<%
		for (Book b : books) {
	%>
	<tr>
		<td align="center"><%=b.getBookId()%></td>
		<td align="center"><%=b.getTitle()%></td>
		<td align="center"><button type="button"
				class="btn btn btn-primary" data-toggle="modal"
				data-target="#myModal1"
				href="editbook.jsp?bookId=<%=b.getBookId()%>">EDIT</button></td>
		<form action="deleteBook" method="post">
		<td align="center"><input type="hidden" name="bookId"
			value=<%=b.getBookId()%>>
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
