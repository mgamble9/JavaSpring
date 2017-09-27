<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questions Dashboard</title>
</head>
<body>
	<h1>Questions Dashboard</h1>
	<table>
		<tr>
			<th>Question:</th>
			<th>tags:</th>
		</tr>
		<c:forEach items="${question_list}" var="q">
			<tr>	
				<td>
					<a href="/questions/${q.id}"><c:out value="${q.question}"/></a>
				</td>
				<td>
					<c:forEach items="${q.tags}" var="t">
					
					<span><c:out value="${t.subject}"/></span><span>,</span>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
		<p><a href="/questions/new">New Question</a></p>
	
</body>
</html>