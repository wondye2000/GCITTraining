<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Borrower"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
//AdministratorService service = new AdministratorService();
	//int count = service.getBorrowersCount();
	int pages = (Integer) request.getAttribute("pages");
	
	//if(count%10==0){
		//pages = count/10;
	//}else{
		//pages = (count/10)+1;
	//}
	
	List<Borrower> borrowers = new ArrayList<Borrower>();
	if(request.getAttribute("borrowers")!=null){
		borrowers = (List<Borrower>)request.getAttribute("borrowers");
	}else{
		//borrowers = service.getBorrowers(1, 10);
	}
%>
<script>
	function searchBorrowers(){
		$.ajax({
			  url: "searchBorrowers",
			  data: { searchString: $('#searchString').val()}
			})
			  .done(function( data ) {
			    $('#borrowersTable').html(data);
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
<form action="searchBorrowers" method="post">
<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search">
<input type="submit" value="Search!" onclick="javascript:searchBorrowers();">
</form>
<h2> View Existing Borrowers</h2>
<nav>
  <ul class="pagination">
  <% for(int i=1;i<=pages;i++){%>
    <li><a href="pageBorrowers?pageNo=<%=i%>"><%=i%></a></li>
    <%} %>
  </ul>
</nav>
<table class="table" id="borrowersTable">
	<tr>
	<th>Borrower ID</th>
	<th>Borrower Name</th>
	<th>Address</th>
	<th>Phone</th>
	<th>Edit</th>
	<th>Delete</th>
	</tr>
	<%for(Borrower b: borrowers){ %>
		<tr>
			<td align="center"><%=b.getCardNo() %></td>
			<td align="center"><%=b.getName() %></td>
			<td align="center"><%=b.getAddress() %></td>
			<td align="center"><%=b.getPhone() %></td>
			<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1"
				href="editborrower.jsp?borrowerId=<%=b.getCardNo()%>">EDIT</button></td>
			form action="deleteBorrrower" method="post">
		<td align="center"><input type="hidden" name="borrowerId"
			value=<%=b.getCardNo()%>>
			<button type="submit" class="btn btn btn-danger"
				>DELETE</button></td>

		</form>
			</tr>
	<%} %>
	
</table>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>
