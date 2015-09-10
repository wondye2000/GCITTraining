<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	//AdministrativeService adminService = new AdministrativeService();

	JDBC jdbc = new JDBC();
	//List<Branch> branch = new ArrayList<Branch>();
	//branch = jdbc.getBranch();

	int branchId = Integer.parseInt(request.getParameter("branchId"));
	Branch branchs = jdbc.getBranch(branchId);
%>
<div class="modal-body">
	<form action="editBranch" method="post">
		<div>
			Enter Branch Name: <input type="text" name="branchName"
				value=<%=branchs.getBranchName()%>> <input
				type="hidden" name="branchId" value=<%=branchId%>> <input
				type="submit" />
		</div>

		<div>
			Enter Branch Address: <input type="text" name="branchAddress"
				value=<%=branchs.getBranchAddress()%>> <input
				type="hidden" name="branchId" value=<%=branchId%>>
		</div>

		
	</form>
</div>