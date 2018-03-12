<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Materials</title>
<style><%@include file="/WEB-INF/css/style.css"%></style> 
</head>
<body>



<form action="MaterialsServlet?action=updateMaterials" method="post">
		<input type="hidden" name="materialId" value="${material.id}">
		
		<p>Item</p>
		<p><input type="text" name="item" value="${material.item}"></p>
		
		<p>Description</p>
		<%-- <p><input type="text" name="description" value="${material.description}"></p> --%>
		<p><textarea rows="5" cols="50" name="description">${material.description}</textarea></p>
		
		<p>Unit Excl</p>
		<p><input type="text" name="unitExcl" value="${material.unitExcl}"></p>
		
		<p>Total Excl</p>
		<p><input type="text" name="totalExcl" value="${material.totalExcl}"></p>
		
		<p>Total Incl</p>
		<p><input type="text" name="totalIncl" value="${material.totalIncl}"></p>
		
		<p><input type="submit" value="Submit"/></p>
		
</form>
		</body>
		</html>
		
		
		
		
		
		