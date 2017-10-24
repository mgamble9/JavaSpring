<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
	<p><a href="/logout">Logout</a></p>
    <h1>Welcome <c:out value="${currentUser.firstName}"></c:out></h1>
	<table class="table">
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>Action</th>
	</tr>
    	<c:forEach items="${user_list}" var="user">
    		<tr>
    	    		<td><c:out value="${user.fullName}"/></td>    
	    		<td><c:out value="${user.email}"/></td>	    		
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
	    </tr>	
   	</c:forEach>	    
</table>
	
</body>
</html>