<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	//AdministratorService service = new AdministratorService();
	//int count = service.getPublishersCount();
	int pages = (Integer) request.getAttribute("pages");
	//if (count % 10 == 0) {
	//	pages = count / 10;
	//} else {
	//	pages = (count / 10) + 1;
	//}

	List<Publisher> publishers = new ArrayList<Publisher>();
	if (request.getAttribute("publishers") != null) {
		publishers = (List<Publisher>) request.getAttribute("publishers");
	} else {
	//	publishers = service.getPublisher(1, 10);
	}
%>
<script>
	function searchPublishers() {
		$.ajax({
			url : "searchPublishers",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#publishersTable').html(data);
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

<form action="searchPublishers" method="post">
	<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search"> <input type="submit"
		value="Search!" onclick="javascript:searchPublishers();">
</form>

<h2>View Existing Publishers</h2>
<nav>
	<ul class="pagination">
		<%
			for (int i = 1; i <= pages; i++) {
		%>
		<li><a href="pagePublishers?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>
	</ul>
</nav>
<table class="table" id="publishersTable" style="width: 100%">
	<tr>
		<th>Publisher ID</th>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th>Edit Publisher</th>
		<th>Delete Publisher</th>
	</tr>
	<%
		for (Publisher p : publishers) {
	%>
	<tr>
		<td align="center"><%=p.getPublisherId()%></td>
		<td align="center"><%=p.getPublisherName()%></td>
		<td align="center"><%=p.getPublisherAddress()%></td>
		<td align="center"><%=p.getPublisherPhone()%></td>
		<td align="center"><button type="button"
				class="btn btn btn-primary" data-toggle="modal"
				data-target="#myModal1"
				href="editpublisher.jsp?publisherId=<%=p.getPublisherId()%>">EDIT</button></td>
		<form action="deletePublisher" method="post">
		<td align="center"><input type="hidden" name="publisherId"
			value=<%=p.getPublisherId()%>>
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
