<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Search</title>
<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>
<body>

<h2>Customer Search</h2>

<form action="CustomersServlet?action=searchForCustomer" method="post">
	
	<p>Enter Search Text</p>
	<input type="text" name="search" required>
	<select name = "select">
			  <option value="Firstname">First name</option>
			  <option value="Surname">Surname</option>
			  <option value="Phonenumber">Phone number</option>
			  <option value="Address">Address</option>
			  <option value="Description">Description</option>
			  <option value="RecommendedBy">Recommended By</option>
			  <option value="StartDate">Start Date</option>
			  <option value="FinishDate">Finish Date</option>
	</select>
	<input type="submit" value="Search for customer">
	
	<p>
			<a href="CustomersServlet?action=">Customers</a>
		</p>

</form>
</body>
</html>