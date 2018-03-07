<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>List of Materials.</h2>

<c:if test="${empty listOfMaterials}">
	There are no entries in the database.
</c:if>

	<c:if test="${not empty listOfMaterials}">
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Item</th>
				<th>Description</th>
				<th>Unit Excl</th>
				<th>Total Excl</th>
				<th>Total Incl</th>
			</tr>
			<c:forEach var="material" items="${listOfMaterials}">
				<tr><td>${material.id}</td>
					<td>${material.description}</td>
					<td>${material.unitExcl}</td>
					<td>${material.totalExcl}</td>
					<td>${material.totalIncl}</td>
					<td><a href="MaterialsServlet?action=delete&materialId=${material.id}">Delete</a></td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
		<p>
			<a href="MaterialsServlet?action=showInsertMaterialsForm">Insert New Material</a>
		</p>
		
</body>
</html>