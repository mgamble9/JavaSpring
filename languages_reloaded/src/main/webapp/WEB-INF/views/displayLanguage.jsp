<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${language.name}"/></title>
</head>
<body>
<p><a href="/languages">Dashboard</a></p>
<p><c:out value="${language.name}"/></p>
<p><c:out value="${language.creator}"/></p>
<p><c:out value="${language.currentVersion}"/></p>
<p><a href="/languages/delete/${id}">Delete</a></p>
<p><a href="/languages/edit/${id}">Edit</a></p>
</body>
</html>