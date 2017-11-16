<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title>Water BnB</title>
</head>
<body>
	<p><a href="/decision">Home</a></p>
	<p><a href="/logout">Logout</a></p>
	<h3>Find your pool!</h3>
	<form action="/search">
		<p><input type="text" name="location" required autofocus placeholder="new search">
	 	<span><button>Search</button></span></p>
	</form>
	
	<table class="table">
		<tr>
			<th>Address</th>
			<th>Pool Size</th>
			<th>Cost/Night</th>
			<th>Details</th>
		</tr>
	    	<c:forEach items="${listing_search_list}" var="listing">
	    		<tr>
		    		<td><c:out value="${listing.address}"/></td>    
		    		<td><c:out value="${listing.pool}"/></td>    
 		    		<td>$<c:out value="${listing.cost}"/></td>
 		    		<c:set var = "rating_sum" value="0"/>
	    			<c:set var = "count" value="0"/>   
 		    		<c:forEach items="${listing.reviews}" var="x">
 		    			<c:set var = "rating_sum" value="${rating_sum + x.rating}"/>
 		    			<c:set var = "count" value="${count + 1}"/>	
 		    		</c:forEach>
 		    		<c:set var = "rating" value = "${rating_sum/count}"/>		    		
 		    		<td><a href="/pools/${listing.id}"><fmt:formatNumber value="${rating}" maxFractionDigits="1" minFractionDigits="1"/>/5 - See More</a></td> 
    	    		</tr>	
   		</c:forEach>	    
	</table>
	
</body>
</html>