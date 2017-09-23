<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Ninja</title>
</head>
<body>

	<h1>New Ninja</h1>
	
	<form:form method="POST" action="/ninjas/new" modelAttribute="ninja">
	   
	   	<p>Dojo:
        		<form:select path="dojo">
  				<form:options items="${dojo_list}" itemValue="id" itemLabel="name"/> 
        		</form:select>
	    </p>
	   
	    <p>First Name:
		    <form:label path="firstName">
		    <form:errors path="firstName"/>
		    <form:input required="true" path="firstName"/></form:label>
	    </p>
 	   	<p>Last Name:
		    <form:label path="lastName">
		    <form:errors path="lastName"/>
		    <form:input required="true" path="lastName"/></form:label>
	    </p>
 	    
 	    	<p>Age:
		    <form:label path="age">
		    <form:errors path="age"/>
		    <form:input type="number" required="true" path="age"/></form:label>
	    </p>
 	    
	    <input type="submit" value="Create"/>
	</form:form>

</body>
</html>