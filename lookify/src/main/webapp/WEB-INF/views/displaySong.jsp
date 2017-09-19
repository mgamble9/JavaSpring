<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details</title>
</head>
<body>
	<p><a href="/dashboard">Dashboard</a></p>
	<br>
	<p>Title: <c:out value="${song.title}"/></p>
	<p>Artist: <c:out value="${song.artist}"/></p>
	<p>Rating(1-10): <c:out value="${song.rating}"/></p>
	<p><a href="/songs/delete/${song.id}">delete</a></p>
</body>
</html>