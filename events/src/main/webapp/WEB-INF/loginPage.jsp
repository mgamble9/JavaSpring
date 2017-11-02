<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login/Register for Events</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">

</head>
<body>
    <h1>Login:</h1>
    <p class="error">
    <c:if test="${registeredMessage != null}">
        <c:out value="${registeredMessage}"></c:out>
    </c:if>
    
    <c:if test="${logoutMessage != null}">
        <c:out value="${logoutMessage}"></c:out>
    </c:if>
    <c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>
    </c:if>
    </p>
    <form method="POST" action="/">
        <p>
            <label for="username">Email:</label>
            <input type="text" id="username" name="username"/>
        </p>
        <p>
            <label for="password">Password:</label>
            <input type="password" id="password" value="password" name="password"/>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login!"/>
    </form>
    
        <h1>Register:</h1>
    
    <p><form:errors path="user.*" cssClass="error"/></p>
    
    <form:form method="POST" action="/registration" modelAttribute="user">
        <p>
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName"/>
        </p>
        <p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/>
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
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" value="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation" value="password"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
    
</body>
</html>
