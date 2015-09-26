<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%
	AdministratorService adminService = new AdministratorService();
	String borrowerId = request.getParameter("borrowerId");
	String borrowerName = request.getParameter("borrowerName");
	String borrowerAddress = request.getParameter("borrowerAddress");
	String borrowerPhone = request.getParameter("borrowerPhone");
	Borrower borrower = adminService.getBorrowerByID(Integer.parseInt(borrowerId));
%>
<div class="modal-body">
	<form action="editBorrower" method="post">
		Enter Borrower Name: <input type="text" name="borrowerName"
			value="<%=borrower.getName()%>"> <input type="hidden"
			name="borrowerId" value=<%=borrower.getCardNo()%>> Enter
		BorrowerAddress: <input type="text" name="borrowerAddress"
			value="<%=borrower.getAddress()%>"> <input type="hidden"
			name="borrowerId" value=<%=borrower.getCardNo()%>> Enter
		Borrower Phone:<input type="text" name="borrowerPhone"
			value="<%=borrower.getPhone()%>"> <input type="hidden"
			name="borrowerId" value=<%=borrower.getCardNo()%>> <input
			type="submit" />
	</form>
</div>