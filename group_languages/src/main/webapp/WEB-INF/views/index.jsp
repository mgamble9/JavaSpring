<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Languages</title>
</head>
<body>
<table class="table">
	<tr>
		<th>Name</th>
		<th>Creator</th>
		<th>Version</th>
		<th>action</th>
	</tr>
<c:forEach items="${languages}" var="languages" varStatus="loop">
    <tr>    
	    <td><a href="/languages/${loop.index}"><c:out value="${languages.name}"/></a></td>
	    <td><c:out value="${languages.creator}"/></td>
	    <td><c:out value="${languages.currentVersion}"/></td>
	    <td>
	    		<a href="/languages/delete/${loop.index}">delete</a>
	    		<a href="/languages/edit/${loop.index}">edit</a>
	    		
	    </td>
    </tr>
</c:forEach>
</table>

<form:form method="POST" action="/languages" modelAttribute="language">
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