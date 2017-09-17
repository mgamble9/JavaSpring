<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Secret Code</title>
</head>
<body>
	
	<div style="padding:10px">
		<p style="color:red"><c:out value="${error}"/></p>
		<p>What is the code?</p>
          <form action='/process_code_guess' method = 'POST'>
            <input type='text' name='code_guess' required>
            <input type='submit' value='Try Code'>
          </form>
	</div>
</body>
</html>