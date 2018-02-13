<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Customer</title>
<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>

<body>
<h2>Insert Customer</h2>

${message}

<form action="CustomersServlet?action=insertCustomer" method="post">
	<p>First name</p>
	<p><input type="text" name="firstname" placeholder="First name" required> </p>
	
	<p>Surname</p>
	<p><input type="text" name="surname" placeholder="Surname" required> </p>
	
	<p>Phone number</p>
	<p><input type="text" name="phonenumber" placeholder="Phone number" required> </p>
	
	<p>address</p>
	<p><!-- <input type="text" name="address" placeholder="address" required> -->
	<textarea name="address" rows="5" cols="50" placeholder="address"></textarea> </p>
	
	<p>Email Address(es) (insert a space between each one)</p>
	<textarea name="emails" rows="5" cols="50" placeholder="Email Addresses"></textarea>
	
	<p>description</p>
	<p><input type="text" name="description" placeholder="description"> </p>
	
	<p>recommendedBy</p>
	<p><input type="text" name="recommendedBy" placeholder="recommendedBy"> </p>
	
	<p>year</p>
	<p><input type="text" name="year" placeholder="year"> </p>
	
	<p>start date</p>
	<p><input type="date" name="startdate"></p>
	
	<p>finish date</p>
	<p><input type="date" name="finishdate"></p>
	<p><input type="submit" value="Insert Customer"></p>
	
	<p>
			<a href="CustomersServlet?action=">Home</a>
		</p>
</form>

</body>
</html>