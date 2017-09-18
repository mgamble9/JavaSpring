<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${language.name}"/></title>
</head>
<body>
	<p><a href="/languages/delete/${id}">Delete</a></p>
	<p><a href="/languages">Dashboard</a></p>

<form:form method="POST" action="/languages/edit/${id}" modelAttribute="language">
    <p>
	    <form:label path="name">Name
	    <form:errors path="name"/>
	    <form:input path="name"/></form:label>
    </p>
    <p>
	    <form:label path="creator">Creator
	    <form:errors path="creator"/>
	    <form:input path="creator"/></form:label>
    </p>
    <p>
	    <form:label path="currentVersion">Version
	    <form:errors path="currentVersion"/>
	    <form:input path="currentVersion"/></form:label>
    </p> 
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>