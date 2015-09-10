<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Publisher</title>
</head>
<body>
	<form action="addPublisher" method="post">
		<div>
			<input type="text" name="publisherName" placeholder="Publisher Name"><input
				type="submit" value="submit">
		</div>

		<div>
			<input type="text" name="publisherAddress" placeholder="Publisher Address">
		</div>

		<div>
			<input type="text" name="publisherPhone" placeholder="Publisher Phone">
		</div>

	</form>
</body>
</html>