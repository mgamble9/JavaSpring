<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
	<p><a href="/logout">Logout</a></p>
    <h1>Welcome <c:out value="${currentUser.firstName}"></c:out></h1>
	<p>Name: <c:out value="${currentUser.fullName}"></c:out></p>
	<p>Email: <c:out value="${currentUser.email}"></c:out></p>
	<p>Sign-up date: <fmt:formatDate dateStyle="MEDIUM" value="${currentUser.createdAt}"/></p>
	<p>Last sign in: <fmt:formatDate dateStyle="MEDIUM" value="${currentUser.updatedAt}"/></p>







<%--     <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
 --%>
 
 </body>
 
</html>
