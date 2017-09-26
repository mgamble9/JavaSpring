<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Category Page</title>
</head>
<body>
	<h1><c:out value="${category.name}"/></h1>
	
	<h2>Products:</h2>
	<c:forEach var="product" items="${category.products}">
    		<li><c:out value="${product.name}"/></li>
	</c:forEach>
	<br>
    <form method="POST" action="/categories/<c:out value="${id}"/>">
		Add Product to Category:
		<select name="product_id" required="true">
			<c:forEach var="prod" items="${product_list}">
			<option value=<c:out value="${prod.id}"/>><c:out value="${prod.name}"/></option>
			</c:forEach>
		</select>
         <p><button>Add</button></p>
    </form>

</body>
</html>