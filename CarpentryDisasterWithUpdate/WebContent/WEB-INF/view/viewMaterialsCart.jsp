<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Materials Cart</title>

<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>
<body>

	<h2>Shopping Cart</h2>
	
	<p>
	Welcome to your shopping cart ${customer.getFirstName()} ${customer.getSurName()}
	</p>
	
	<c:set var="total"/>
	
	
	
	<c:if test="${empty allCartMaterials}">
	There are no entries in the Cart database.
	</c:if>
	
	<c:if test="${not empty allCartMaterials}">
	There are entries in the Cart database.
	
	
	
	<table border="1">
			<tr><th>cartId</th>
				<th>customerId</th>
				<th>materialId</th>
				<th>quantity</th>
				<th>Description</th>
				<th>Price</th>
				<th>Total</th>
				<th>Add</th>
				<th>Remove</th>
			</tr>
			<c:forEach var="material" items="${allCartMaterials}">
				<tr>
					<c:forEach  var ="materialID" items="${allMaterials}">
					
						<c:if test="${materialID.id==material.materialId}">
							<td>${material.cartId}</td>
							<td>${material.customerId}</td>
							<td>${material.materialId}</td>				
							<td>${material.quantity}</td>	
							
							<td> ${materialID.description}</td>
							<td>${materialID.totalIncl}</td>
							<td>${materialID.totalIncl*material.quantity}</td>
							
							<c:set var="total" value="${total + materialID.totalIncl*material.quantity}"/>
							
							<td><a href="CartServlet?action=addCartMaterial2&materialId=${material.materialId}&customerId=${material.customerId}">Add</a></td>
							<td><a href="CartServlet?action=deleteCartMaterial&materialId=${material.materialId}&customerId=${material.customerId}">Remove</a></td>
														
						</c:if>					
					</c:forEach>					
				</tr>
			</c:forEach>
		</table>
</c:if>
	<p>
	Total cost: &euro; ${total}
	</p>
	<p>
			<a href="CustomersServlet?action=">Customers</a>
		</p>
		<P>
			<a href="MaterialsServlet?action=viewAll&customerId=${customer.id}">Materials</a>
		</P>
</body>
</html>