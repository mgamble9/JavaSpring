<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Question</title>
</head>
<body>
	<h1>What is your Question?</h1>
	
		<p style="color:red"><c:out value="${error}"/></p>
		<form:form method="POST" action="/questions/new" modelAttribute="question_new">
		    <p>Question:
			    <form:label path="question">
			    <form:errors path="question"/>
			    <form:input required="true" path="question"/></form:label>
		    </p>
		    <p>Tags:
		    		<input type="text" required="true" name="tags_input_str"/>
		    	</p>   		    
		    <p><input type="submit" value="Submit"/></p>
		</form:form>
		
</body>
</html>