<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.gcit.lms.domain.Branch"%>
<%@ page import="com.gcit.lms.service.AdministratorService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
//AdministratorService service = new AdministratorService();
	//int count = service.getBranchCount();
	int pages = (Integer) request.getAttribute("pages");
	
	//if(count%10==0){
		//pages = count/10;
	//}else{
		//pages = (count/10)+1;
	//}
	
	List<Branch> branch = new ArrayList<Branch>();
	if(request.getAttribute("branchs")!=null){
		branch = (List<Branch>)request.getAttribute("branchs");
	}else{
		//branch = service.getBranch(1, 10);
	}
%>
<script>
	function searchBranchs(){
		$.ajax({
			  url: "searchBranchs",
			  data: { searchString: $('#searchString').val()}
			})
			  .done(function( data ) {
			    $('#branchsTable').html(data);
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

<form action="searchBranchs" method="post">
<input type="text" class="col-md-8" name="searchString"
		placeholder="Enter title name to search">
<input type="submit" value="Search!" onclick="javascript:searchBranchs();">
</form>
<h2> View Existing Branches</h2>
<nav>
  <ul class="pagination">
  <% for(int i=1;i<=pages;i++){%>
    <li><a href="pageBranchs?pageNo=<%=i%>"><%=i%></a></li>
    <%} %>
  </ul>
</nav>
<table class="table" id="branchsTable">
	<tr>
	<th>Branch ID</th>
	<th>Branch Name</th>
	<th>Edit Branch</th>
	<th>Delete Branch</th>
	</tr>
	<%for(Branch b: branch){ %>
		<tr>
			<td align="center"><%=b.getBranchId() %></td>
			<td align="center"><%=b.getBranchName() %></td>
			<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1"
				href="editbranch.jsp?branchId=<%=b.getBranchId()%>">EDIT</button></td>
			<form action="deleteBranch" method="post">
		<td align="center"><input type="hidden" name="branchId"
			value=<%=b.getBranchId()%>>
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
