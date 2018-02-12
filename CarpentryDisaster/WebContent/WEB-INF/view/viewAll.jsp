<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Customers</title>
<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>
<body>
	<h2>List of Customers from the Address Book.</h2>

<c:if test="${empty listOfCustomers}">
	There are no entries in the database.
</c:if>

	<c:if test="${not empty listOfCustomers}">
		<table border="1">
			<tr>
				<th>Firstname</th>
				<th>Surname</th>
				<th>PhoneNumber</th>
				<th>Address</th>
				<th>Emails</th>
				<th>Description</th>
				<th>Recommended by</th>
				<th>Year</th>
				<!-- <th>Emails</th> -->
			</tr>
			<c:forEach var="customer" items="${listOfCustomers}">
				<tr>
					<td>${customer.firstName}</td>
					<td>${customer.surName}</td>
					<td>${customer.phoneNumber}</td>
					<td>${customer.address}</td>
					
					<td><c:forEach var="email" items="${customer.emails}">
							${email.email} <br>
						</c:forEach>
					</td>
					<td>${customer.description}</td>
					<td>${customer.recommendedBy}</td>
					<td>${customer.year}</td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
		<p>
			<a href="CustomersServlet?action=showInsertCustomerForm">Insert New Customer</a>
		</p>
		<p>
			<a href="CustomersServlet?action=showCustomerSearchForm">Search for Customer</a>
		</p>
</body>
</html>