<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Person</title>
</head>
<body>
	<h1>New Person</h1>
	
	<form:form method="POST" action="/persons/new" modelAttribute="person">
	    <p>
		    <form:label path="firstName">
		    <form:errors path="firstName"/>
		    <form:input required="true" placeholder="First Name" path="firstName"/></form:label>
	    </p>
	    	<p>
		    <form:label path="lastName">
		    <form:errors path="lastName"/>
		    <form:input required="true" placeholder="Last Name" path="lastName"/></form:label>
	    </p>
	    
	    <input type="submit" value="Create"/>
	</form:form>
	

</body>
</html>