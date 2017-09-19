<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><a href="/dashboard">Dashboard</a></p>
	<table class="table">
		<tr>
			<th>Rating</th>
			<th>Title</th>
			<th>Artist</th>
		</tr>
		<c:forEach items="${songs}" var="song">
		    <tr>    
			    <td><c:out value="${song.rating}"/></td>
			    <td><a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
			    <td><c:out value="${song.artist}"/></td>	    
		    </tr>
		</c:forEach>
	</table>

</body>
</html>