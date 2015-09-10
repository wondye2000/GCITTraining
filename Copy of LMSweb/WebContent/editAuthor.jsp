<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	//AdministrativeService adminService = new AdministrativeService();

	JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();

	int authorId = Integer.parseInt(request.getParameter("authorId"));
	Author author = jdbc.getAuthor(authorId);
%>
<div class="modal-body">
	<form action="editAuthor" method="post">
		<div>
			Enter Author Name: <input type="text" name="authorName"
				value=<%=author.getAuthorName()%>> <input type="hidden"
				name="authorId" value=<%=authorId%>> <input type="submit" />
		</div>

		<div>
			Select Book: <select name="bookId" multiple="multiple">
				<%
					for (Book b : books) {
				%>
				<option value=<%=b.getBookId()%>><%=b.getTitle()%></option>
				<%
					}
				%>
			</select>
		</div>

	</form>
</div>