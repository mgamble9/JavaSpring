<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<title>Course Dashboard</title>
</head>
<body>
	<h1>Course Dashboard</h1>
	
	<form method="POST" action=/courses>
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
			<th>Capacity</th>
			<th>Action</th>
		</tr>
	    	<c:forEach items="${course_list}" var="course">
	    		<tr>
		    		<td><c:out value="${course.name}"/></td>    
		    		<td><c:out value="${course.instructor}"/></td>    
 		    		<td><c:out value="${course.days}"/></td>
 		    		<td><c:out value="${course.times}"/></td>
 		    		<td>
 		    			<c:choose>
						<c:when test = "${course.users.size() == course.capacity}">
							Full
						</c:when>
						<c:otherwise>
							<c:out value="${course.users.size()}"/>/<c:out value="${course.capacity}"/>
						</c:otherwise>	
					</c:choose>	
 		    		</td>
 		    		<td>
 		    			<c:choose>
 		    				<c:when test = "${course.users.contains(current_user)}">
 		    					<a href="/drop/${course.id}">Drop</a>
 		    				</c:when>
 		    				<c:otherwise>
 		    					<c:set var = "add_test" value="1"/>
							<c:set var = "count" value="0"/>   								  
							<c:if test = "${course.users.size() == course.capacity}">
								<c:set var = "add_test" value="0"/>							
							</c:if>
		    					<c:forEach items = "${current_user.courses}" var = "x">
		    						<c:if test = "${x.semester == semester_list[semester_session_var]}">
									<c:set var = "count" value="${count + 1}"/>
									<c:if test = "${count == 4}">
										<!-- 4 courses already! -->
										<c:set var = "add_test" value="0"/>							
									</c:if>	
		    							<c:if test = "${course.days.contains(x.days)}">
		    								<!-- <p>course day checks</p> -->
			    							<c:if test = "${x.times.startsWith(course.times.substring(0,3))}">
			    								<!-- <p>course time checks</p> -->
											<c:set var = "add_test" value="0"/>   
		    								</c:if>
		    							</c:if>
		    						</c:if>
		    					</c:forEach>
    							<c:choose>
    								<c:when test = "${add_test == 0}">
    									Add	
    								</c:when>
    								<c:otherwise>
    									<a href="/add/${course.id}">Add</a>
    								</c:otherwise>
    							</c:choose>
						</c:otherwise>	
					</c:choose>		
 		    		</td> 		    		
    	    		</tr>	
   		</c:forEach>	    
	</table>
	
</body>
</html>