<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title>Home</title>
</head>
<body>

	<h1>Welcome, <c:out value="${current_user.username}"/>!</h1>
	<p><a href="/logout">Logout</a></p>
	<p>This is your student sign-up portal. Check your</p>
	<p>class schedule and find courses for your next term.</p>
	
	<h2>My Schedule</h2>
 		<form method="POST" action=/home>
		<select onchange="submit()" name="semester">
			<c:forEach items="${semester_list}" var="x" varStatus="count">
				<c:choose>
					<c:when test="${semester_session_var == count.index}">
						<option value ="${count.index}" selected><c:out value="${x}"/></option>
					</c:when>
					<c:otherwise>
		 		    		<option value ="${count.index}"><c:out value="${x}"/></option>				
					</c:otherwise>
				</c:choose>
 		    </c:forEach>
		</select>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>		
  		<br>
		<table class="table">
			<tr>
				<th>Course Name</th>
				<th>Instructor</th>
				<th>Day(s)</th>
				<th>Time(s)</th>
				<th>Action</th>
			</tr>
		    	<c:forEach items="${course_list}" var="course">
		    		<c:if test = "${course.users.contains(current_user)}">
			    		<tr>
				    		<td><c:out value="${course.name}"/></td>    
				    		<td><c:out value="${course.instructor}"/></td>    
		 		    		<td><c:out value="${course.days}"/></td>
		 		    		<td><c:out value="${course.times}"/></td>
		 		    		<td>
		 		    			<a href="/drop2/${course.id}">Drop</a>
		 		    		</td> 		    		
		    	    		</tr>
	    	    		</c:if>	
	   		</c:forEach>	    
		</table>
  	
  	<h4><a href="/courses">Find Courses</a></h4>
  	
</body>
</html>