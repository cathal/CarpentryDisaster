<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<form action="CustomersServlet?action=updateCustomer" method="post">
		<input type="hidden" name="customerId" value="${customer.id}">
		
		<p>First name</p>
		<p><input type="text" name="firstname" value="${customer.firstName}"></p>
		
		<p>Surname</p>
		<p><input type="text" name="surname" value="${customer.surName}"></p>
		
		<p>Phone number</p>
		<p><input type="text" name="phonenumber" value="${customer.phoneNumber}"></p>
		
		<p>Address</p>
		<p><textarea rows="5" cols="50" name="address">${customer.address}</textarea></p>
		
		
		<c:set var="allEmails" value=""/>
		<c:forEach var="email" items="${customer.emails}">
			<c:set var="allEmails" value="${allEmails.concat(email.email).concat(' ')}"/>
		</c:forEach>
		<p>
		
		</p>
		<p>Email Address(es) (insert a space between each one)</p>
		<p><textarea rows="5" cols="50" name="emails">${allEmails}</textarea> </p>
		
		<p>Description</p>
		<p><textarea rows="5" cols="50" name="description">${customer.description}</textarea></p>
		
		<p>Recommended By</p>
		<p><input type="text" name="recommendedBy" value="${customer.recommendedBy}"></p>
		
		<p>Year</p>
		<p><input type="text" name="year" value="${customer.year}"></p>
		
		<p>Start date</p>
		<p><input type="date" name="startdate" value="${customer.startdate}"></p>
		
		<p>Finish date</p>
		<p><input type="date" name="finishdate" value="${customer.finishdate}"></p>
		
		<p><input type="submit" value="Submit"/></p>
		
		
</form>
</body>
</html>