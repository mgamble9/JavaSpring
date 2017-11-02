<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title><c:out value="${event.name}"></c:out></title>
</head>
<body>
	<h1><c:out value="${event.name}"></c:out>:</h1>
	<p>Host: <c:out value="${event.host.fullName}"></c:out></p>
	<p>Date: <fmt:formatDate dateStyle="MEDIUM" value="${event.event_date}"/></p>
	<p>Location: <c:out value="${event.location}"></c:out></p>
	<p>People who are attending this event: <c:out value="${attendee_list_size}"></c:out></p>
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Location</th>
		</tr>
	    	<c:forEach items="${attendee_list}" var="x">
	    		<tr>
		    		<td><c:out value="${x.fullName}"/></td>    
		    		<td><c:out value="${x.location}"/></td>     
    	    		</tr>	
   		</c:forEach>	    
	</table>
	
	<h1>Message Wall:</h1>
	<div class="message_wall">
		<c:forEach items="${comment_list}" var="comment">
	    		<p><c:out value="${comment.user.fullName}"/> says: <c:out value="${comment.comment_made}"/></p>
	    		<p>******</p>
   		</c:forEach>	  	
	</div>
	
	<p><form:errors path="comment.*" cssClass="error"/></p>
    <form:form method="POST" action="/events/${id}" modelAttribute="comment">
    		<form:hidden path="user" value="${currentUser.id}"/>
    		<form:hidden path="event" value="${event.id}"/>
        <p>
            <form:label path="comment_made">Add Comment:</form:label>
            <form:textarea cols="50" rows="2" path="comment_made"/>
        </p>
        	<input type="submit" value="Submit!"/>
    </form:form>
	
</body>
</html>