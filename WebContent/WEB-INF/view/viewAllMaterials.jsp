<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>
<body>
<h2>List of Materials.</h2>

<%-- <c:if test="${not empty allCartMaterials}">
	<a href="CartServlet?action=viewMaterialsCart">View Materials Cart</a>
</c:if> --%>


<c:if test="${empty listOfMaterials}">
	There are no entries in the database.
</c:if>

	<c:if test="${not empty listOfMaterials}">
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Quantity</th>
				<th>Sell Unit</th>
				<th>Item</th>
				<th>Description</th>
				<th>Unit Excl</th>
				<th>Total Excl</th>
				<th>Total Incl</th>
				<th>Delete</th>
				<th>Update</th>
				<th>Add to cart</th>
				<!-- <th>View Cart</th> -->
				
			</tr>
			<c:forEach var="material" items="${listOfMaterials}">
				<tr>
					<td>${material.id}</td>
					<td>1</td>
					<td>EACH</td>
					<td>${material.item}</td>
					<td>${material.description}</td>
					<td>${material.unitExcl}</td>
					<td>${material.totalExcl}</td>
					<td>${material.totalIncl}</td>
					<td><a href="MaterialsServlet?action=delete&materialId=${material.id}&customerId=${customerId}">Delete</a></td>
					<td><a href="MaterialsServlet?action=showUpdateForm&materialId=${material.id}&customerId=${customerId}">Update</a></td>
					<td><a href="CartServlet?action=addCartMaterial&materialId=${material.id}&customerId=${customerId}">Add to cart</a></td>
					<%-- <td><a href="CartServlet?action=viewCart&materialId=${material.id}&customerId=${customerId}">View Cart</a></td> --%>
				</tr>
			</c:forEach>
		</table>
	</c:if>
		<p>
			<a href="MaterialsServlet?action=showInsertMaterialsForm&customerId=${customerId}">Insert New Material</a>
		</p>
		<p>
			<%-- <a href="CartServlet?action=viewCart&materialId=${material.id}&customerId=${customerId}">View Cart</a> --%>
			<a href="CartServlet?action=viewCart&customerId=${customerId}">View Cart</a>
		</p>
		<p>
			<a href="CustomersServlet?action=">Customers</a>
		</p>
		
</body>
</html>