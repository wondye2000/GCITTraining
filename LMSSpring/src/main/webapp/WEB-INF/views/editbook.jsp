<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%AdministratorService adminService = new AdministratorService();
 String bookId = request.getParameter("bookId");
 Book book = adminService.getBookByID(Integer.parseInt(bookId));
%>
<div class="modal-body">
<form action="editBook" method="post">
			Enter Book Title: <input type="text" name="bookName" value="<%=book.getTitle()%>">
			<input type="hidden" name="bookId" value=<%=book.getBookId() %>>
		<input type="submit"/>
</form>
</div>