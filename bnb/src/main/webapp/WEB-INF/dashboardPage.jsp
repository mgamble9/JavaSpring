<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title>Water BnB</title>
</head>
<body>
	<h3>Current Listings:</h3>
	<p><a href="/logout">Logout</a></p>
	<table class="table">
		<tr>
			<th>Address</th>
			<th>Pool Size</th>
			<th>Cost/Night</th>
			<th>Details</th>
		</tr>
	    	<c:forEach items="${hosts_listings}" var="listing">
	    		<tr>
		    		<td><c:out value="${listing.address}"/></td>    
		    		<td><c:out value="${listing.pool}"/></td>    
 		    		<td><c:out value="${listing.cost}"/></td>
 		    		
 		    		<c:set var = "rating_sum" value="0"/>
	    			<c:set var = "count" value="0"/>   
 		    		<c:forEach items="${listing.reviews}" var="x">
 		    			<c:set var = "rating_sum" value="${rating_sum + x.rating}"/>
 		    			<c:set var = "count" value="${count + 1}"/>	
 		    		</c:forEach>
 		    		<c:set var = "rating" value = "${rating_sum/count}"/>
 		    				    		
 		    		<td><a href="/host/pools/${listing.id}">
 		    			<fmt:formatNumber value="${rating}" maxFractionDigits="1" minFractionDigits="1"/>/5 - edit</a></td> 
    	    		</tr>	
   		</c:forEach>	    
	</table>
	
	<h3>New Listing:</h3>
	
	    <p><form:errors path="listing.*" cssClass="error"/></p>
    
    <form:form method="POST" action="/host/dashboard" modelAttribute="listing">
  		<form:hidden path="user" value="${current_user.id}"/>
        <p>
            <form:label path="address">Address:</form:label>
            <form:input path="address"/>
        </p>
        <p>
            <form:label path="description">Description:</form:label>
            <form:textarea cols="50" rows="2" path="description"/>
        </p>
        <p>
            <form:label path="cost">Cost/Night:</form:label>
            <form:input type="number" value="100" path="cost"/>
        </p>
        <p>
            <form:label path="pool">Pool Size:</form:label>
        		<form:select path="pool">
  				<form:options items="${pool_size_list}"/> 
        		</form:select>
        </p>
        
        <input type="submit" value="Add Listing!"/>
    </form:form>
	
</body>
</html>