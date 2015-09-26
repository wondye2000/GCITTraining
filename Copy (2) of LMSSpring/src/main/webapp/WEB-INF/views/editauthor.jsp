<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%AdministratorService adminService = new AdministratorService();
 String authorId = request.getParameter("authorId");
 Author author = adminService.getAuthorByID(Integer.parseInt(authorId));
%>
<div class="modal-body">
<form action="editAuthor" method="post">
			Enter Author Name: <input type="text" name="authorName" value="<%=author.getAuthorName()%>">
			<input type="hidden" name="authorId" value=<%=author.getAuthorId() %>>
		<input type="submit"/>
</form>
</div>