<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Product</title>
</head>
<body>
	<h1>New Product</h1>
	
	<form:form method="POST" action="/products/new" modelAttribute="product">
	    <p>Name:
		    <form:label path="name">
		    <form:errors path="name"/>
		    <form:input required="true" path="name"/></form:label>
	    </p>
 	   	<p>Description:
		    <form:label path="description">
		    <form:errors path="description"/>
		    <form:input required="true" path="description"/></form:label>
	    </p>
 	    <p>Price:
		    <form:label path="price">
		    <form:errors path="price"/>
		    <form:input required="true" path="price"/></form:label>
	    </p>
 	    
	    <input type="submit" value="Create"/>
	</form:form>
	
	
</body>
</html>