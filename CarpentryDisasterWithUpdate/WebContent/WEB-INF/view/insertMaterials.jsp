<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Materials</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<h2>Insert Materials</h2>

${message}

<form action="MaterialsServlet?action=insertMaterials" method="post">
	<p>Item</p>
	<p><input type="text" name="item" placeholder="Item" required> </p>
	
	<p>Description</p>
	<!-- <p><input type="text" name="description" placeholder="Description" required> </p> -->
	<p>
	<textarea name="description" rows="2" cols="50" placeholder="Description"></textarea>
	</p>
	
	<p>Unit Excl</p>
	<p><input type="text" name="unitExcl" placeholder="Unit Excl" required> </p>
	
	<p>Total Excl</p>
	<p><input type="text" name="totalExcl" placeholder="Total Excl" required> </p>
	
	<p>Total Incl</p>
	<p><input type="text" name="totalIncl" placeholder="Total Incl" required> </p>
	
	<p><input type="submit" value="Insert Material"></p>
	
	<p>
			<a href="MaterialsServlet?action=">Home</a>
		</p>
</form>

</body>
</html>