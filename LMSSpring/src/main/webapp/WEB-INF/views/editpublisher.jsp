<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%AdministratorService adminService = new AdministratorService();
 String publisherId = request.getParameter("publisherId");
 String publisherAddress = request.getParameter("publisherAddress");
 String publisherPhone = request.getParameter("publisherPhone");
 Publisher publisher = adminService.getPublisherByID(Integer.parseInt(publisherId));
%>
<div class="modal-body">
<form action="editPublisher" method="post">
			Enter Publisher Name: <input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>">
			Enter Publisher Address: <input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>">
			Enter Publisher Phone: <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>">
			<input type="hidden" name="publisherId" value=<%=publisher.getPublisherId() %>>
		<input type="submit"/>
</form>
</div>