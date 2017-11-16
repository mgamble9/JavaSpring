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
	<p><c:out value="${listing.address}"/></p>
	<p><c:out value="${listing.description}"/></p>
	<p>Email: <c:out value="${listing.user.email}"/></p>
	<p>Name: <c:out value="${listing.user.full_name}"/></p>
	<p>Pool Size: <c:out value="${listing.pool}"/></p>
	<p>Cost: $<c:out value="${listing.cost}"/></p>
	
	<c:set var = "rating_sum" value="0"/>
	<c:set var = "count" value="0"/>   
	<c:forEach items="${listing.reviews}" var="x">
		<c:set var = "rating_sum" value="${rating_sum + x.rating}"/>
		<c:set var = "count" value="${count + 1}"/>	
	</c:forEach>
	<c:set var = "rating" value = "${rating_sum/count}"/>	
	<p>Reviews (<fmt:formatNumber value="${rating}" maxFractionDigits="1" minFractionDigits="1"/>/5): 
		<span><a href="/pools/${id}/review">Leave a review</a></span></p>    		
	
	<div class="message_wall">
		<c:forEach items="${review_list}" var="review">
	  		<p><c:out value="${review.user.full_name}"/></p>
	  		<p>Rating: <c:out value="${review.rating}"/>/5</p>
	  		<p><c:out value="${review.review_txt}"/></p>
	  		<p>******</p>
		</c:forEach>	  	
	</div>
	
</body>
</html>