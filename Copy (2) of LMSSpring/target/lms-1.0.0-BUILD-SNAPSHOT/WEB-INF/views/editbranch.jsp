<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%AdministratorService adminService = new AdministratorService();
 String branchId = request.getParameter("branchId");
 Branch branch = adminService.getBranchByID(Integer.parseInt(branchId));
%>
<div class="modal-body">
<form action="editBranch" method="post">
			Enter Branch Name: <input type="text" name="branchName" value="<%=branch.getBranchName()%>">
			<input type="hidden" name="branchId" value=<%=branch.getBranchId() %>>
		<input type="submit"/>
</form>
</div>