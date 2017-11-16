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
    <form method="POST" action="/guest/signin">
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
            <form:label path="first_name">First Name:</form:label>
            <form:input path="first_name" value="Alf"/>
        </p>
        <p>
            <form:label path="last_name">Last Name:</form:label>
            <form:input path="last_name" value="Alfson"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email" value="alf@alf.com"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" value="password"/>
        </p>
        <p>
            <form:label path="conf_password">Password Confirmation:</form:label>
            <form:password path="conf_password" value="password"/>
        </p>
        <p>
        		<form:select path="role">
  				<form:options items="${user_role_option_list}"/> 
        		</form:select>
        </p>
        
        <input type="submit" value="Register!"/>
    </form:form>
    
</body>
</html>