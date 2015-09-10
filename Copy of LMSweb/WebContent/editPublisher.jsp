<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.dababase.JDBC"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	//AdministrativeService adminService = new AdministrativeService();

	JDBC jdbc = new JDBC();
	List<Book> books = new ArrayList<Book>();
	books = jdbc.getBooks();

	int publisherId = Integer.parseInt(request.getParameter("publisherId"));
	Publisher publisher = jdbc.getPublisher(publisherId);
%>
<div class="modal-body">
	<form action="editPublisher" method="post">
		<div>
			Enter Publisher Name: <input type="text" name="publisherName"
				value=<%=publisher.getPublisherName()%>> <input
				type="hidden" name="publisherId" value=<%=publisherId%>> <input
				type="submit" />
		</div>

		<div>
			Enter Publisher Address: <input type="text" name="publisherAddress"
				value=<%=publisher.getPublisherAddress()%>> <input
				type="hidden" name="publisherId" value=<%=publisherId%>>
		</div>

		<div>
			Enter Publisher Phone: <input type="text" name="publisherPhone"
				value=<%=publisher.getPublisherPhone()%>> <input
				type="hidden" name="publisherId" value=<%=publisherId%>>
		</div>



	</form>
</div>