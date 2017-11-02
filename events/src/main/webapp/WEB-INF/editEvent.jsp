<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title>Events</title>
</head>
<body>
	<h1><c:out value="${event.name}"></c:out></h1>
    <h4>Edit Event:</h4>
    <p><form:errors path="event.*" cssClass="error"/></p>
    <form:form method="POST" action="/events/edit" modelAttribute="event">
    		<form:hidden path="host"/>
    		<form:hidden path="id"/>	
     	<form:hidden path="createdAt"/>	
        <p>
            <form:label path="name">Name:</form:label>
            <form:input path="name"/>       
        </p>
        <p>
            <form:label path="event_date">Date
        		<form:input type="date" required="true" path="event_date"/></form:label>
        	</p>
        	        <p>
        		<form:label path="city">Location:</form:label>
        		<form:input path="city"/>
        		<span>
        			<form:select path="state">
  					<form:options items="${us_state_list}"/> 
        			</form:select>
        		</span>
        </p>
        	<input type="submit" value="Edit!"/>
    </form:form>

</body>
</html>