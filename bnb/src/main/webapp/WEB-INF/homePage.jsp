<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Water BnB</title>
</head>
<body>
	<p><a href="/guest/signin">Signin/Signup</a></p>
	<h3>Find places to swim and sleep on Water BnB!</h3>
	<form action="/search">
<%-- 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 --%>
 		<p><input type="text" name="location" required autofocus placeholder="location">
	 	<span><button>Search</button></span></p>
	</form>
	
</body>
</html>