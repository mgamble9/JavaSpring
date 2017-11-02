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
<title>Events</title>
</head>
<body>
	<p><a href="/logout">Logout</a></p>
    <h2>Welcome, <c:out value="${currentUser.firstName}"></c:out>.</h2>
    <p>Here are the events in your state:</p>
    	<table class="table">
		<tr>
			<th>Name</th>
			<th>Date</th>
			<th>Location</th>
			<th>Host</th>
			<th>Action/Status</th>
		</tr>
	    	<c:forEach items="${event_list_in_state}" var="event">
	    		<tr>
	    	    		<td><a href="/events/${event.id}">${event.name}</a></td>    
		    		<td><fmt:formatDate dateStyle="MEDIUM" value="${event.event_date}"/></td>
		    		<td><c:out value="${event.location}"/></td>    
 		    		<td><c:out value="${event.host.firstName}"/></td>	
 		    		<td>
 		    			<c:choose>
	 		    			<c:when test = "${event.host.id == currentUser.id}">
			   				<a href="/events/${event.id}/edit">Edit</a>
	 		    				<a href="/events/delete/${event.id}">Delete</a>
	 		    			</c:when>
	 		    			<c:otherwise>
	 		    				<c:set var = "attendance_test" value="0"/> 			
 		    						<c:forEach items="${event.attendees}" var="attendee">
 		    							<c:if test = "${attendee.id == currentUser.id}">
 		    								<c:set var = "attendance_test" value="1"/>
  		    								<p>Joining <a href="/events/cancel/${event.id}">Cancel</a></p>
 		    							</c:if>
 		    						</c:forEach>
 		    					<c:if test = "${attendance_test == 0}">
	 		    					<a href="/events/join/${event.id}">Join</a>
 		    					</c:if>
	 		    			</c:otherwise>
 		    			</c:choose>
 		    		</td>   		  		
<%-- 		    		
				THIS CODE IS FOR ROLE FUNCTIONS
				<td>
		    			<c:forEach items="${user.roles}" var="role">
		    				<c:if test = "${role.name == 'ROLE_ADMIN'}">
							<p>admin</p>
		 				</c:if>
		    				<c:if test = "${role.name == 'ROLE_USER'}">
		    					<a href="/admin/delete/${user.id}">delete</a>
		   					<a href="/admin/make_admin/${user.id}">make admin</a>
		 				</c:if>
		 				    			
					</c:forEach>
		    		</td>
 --%>
 	    		</tr>	
   		</c:forEach>	    
	</table>
    
    <p>Here are the events in other states:</p>
        	<table class="table">
		<tr>
			<th>Name</th>
			<th>Date</th>
			<th>City/Town</th>
			<th>State</th>
			<th>Host</th>
			<th>Action/Status</th>
		</tr>
	    	<c:forEach items="${event_list_out_of_state}" var="e">
	    		<tr>
	    	    		<td><a href="/events/${e.id}">${e.name}</a></td>    
		    		<td><fmt:formatDate dateStyle="MEDIUM" value="${e.event_date}"/></td>
		    		<td><c:out value="${e.city}"/></td>    
		    		<td><c:out value="${e.state}"/></td>    
 		    		<td><c:out value="${e.host.firstName}"/></td>
  		    		<td>
 		    			<c:choose>
	 		    			<c:when test = "${e.host.id == currentUser.id}">
			   				<a href="/events/${e.id}/edit">Edit</a>
	 		    				<a href="/events/delete/${e.id}">Delete</a>
	 		    			</c:when>
	 		    			<c:otherwise>
	 		    				<c:set var = "attendance_test" value="0"/> 			
 		    						<c:forEach items="${e.attendees}" var="attendee">
 		    							<c:if test = "${attendee.id == currentUser.id}">
 		    								<c:set var = "attendance_test" value="1"/>
  		    								<p>Joining <a href="/events/cancel/${e.id}">Cancel</a></p>
 		    							</c:if>
 		    						</c:forEach>
 		    					<c:if test = "${attendance_test == 0}">
	 		    					<a href="/events/join/${e.id}">Join</a>
 		    					</c:if>
	 		    			</c:otherwise>
 		    			</c:choose>
 		    		</td>   		  		
 
    	    		</tr>	
   		</c:forEach>	    
	</table>
    
    <h3>Create an Event:</h3>
    <p><form:errors path="event.*" cssClass="error"/></p>
    <form:form method="POST" action="/create_event" modelAttribute="event">
    		<form:hidden path="host" value="${currentUser.id}"/>
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
        	<input type="submit" value="Submit!"/>
    </form:form>
        	
        
</body>
</html>