<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagination And Sorting</title>
</head>
<body>
<div id="ninjas">
    <h1>Ninjas</h1>
    
<!--     for loop to create all the links
    we have to call the .content method to actually get the
    ninjas inside the Page iterable.
 -->    
    <c:forEach begin="1" end="${totalPages}" var="index">
        <a href="/pages/${index}">${index}</a>
    </c:forEach>
    <table class="table">
        <tr>
            <th>Dojo Name</th>
            <th>Ninja Name</th>            
        </tr>
     
            <c:forEach var="ninja" items="${ninjas.content}">                 
            <tr>
                <td><c:out value="${ninja.dojo.name}"></c:out></td>
                <td><c:out value="${ninja.fullName}"></c:out></td>
            </tr>
            </c:forEach>
    </table>
</div>
</body>
</html>