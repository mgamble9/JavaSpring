<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Profile</title>
</head>
<body>
	<h1><c:out value="${question.question}"/></h1><a href="/questions">dashboard</a>
	<h3>Tags:</h3>
		<c:forEach items="${question.tags}" var="t">
			<span><c:out value="${t.subject}"/></span><span>,</span>
		</c:forEach>
	<br>
	<br>
	
	<table>
		<tr>
			<th>Answers:</th>
		</tr>
		<c:forEach items="${answer_list}" var="answer">
			<tr>	
				<td>
					<c:out value="${answer.answer}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h3>Add your Answer:</h3>
	
	<form:form method="POST" action="/questions/${id_uri}" modelAttribute="answer_new">
 		<p>Answer:
			<form:label path="answer">
			<form:errors path="answer"/>
			<form:input required="true" path="answer"/></form:label>
		</p>
		<input type="submit" value="Answer it!"/>		
	</form:form>
	
</body>
</html>