<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Page</title>
</head>
<body>
	<h1><c:out value="${product.name}"/></h1>
	
	<h2>Categories</h2>
	<c:forEach var="category" items="${product.categories}">
    		<p><c:out value="${category.name}"/></p>
	</c:forEach>
	
    <form method="POST" action="/products/<c:out value="${id}"/>">
		Add Category:
		<select name="category_id" required="true">
			<c:forEach var="cat" items="${category_list}">
			<option value=<c:out value="${cat.id}"/>><c:out value="${cat.name}"/></option>
			</c:forEach>
		</select>
         <p><button>Add</button></p>
    </form>
	
	
</body>
</html>