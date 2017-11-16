<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<p>Review of: <c:out value="${listing.address}"/></p>
		    <p><form:errors path="review.*" cssClass="error"/></p>
    
    <form:form method="POST" action="/pools/${id_listing}/review" modelAttribute="review">
  		<form:hidden path="user" value="${current_user.id}"/>
  		<form:hidden path="listing" value="${listing.id}"/>
        <p>
            <form:textarea cols="50" rows="2" path="review_txt"/>
        </p>
        <p>Rating:
        		<form:select path="rating">
        			<c:forEach var="i" begin="1" end ="5">
        				<form:option value="${i}"/>
        			</c:forEach>
        		</form:select>
        </p>
        
        <input type="submit" value="Submit Review!"/>
    </form:form>
	
</body>
</html>