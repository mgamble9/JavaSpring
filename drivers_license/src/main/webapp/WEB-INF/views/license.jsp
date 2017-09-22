<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New License</title>
</head>
<body>
	<h1>New License</h1>
	
	<form:form method="POST" action="/licenses/new" modelAttribute="license">
		<form:hidden path="number"/>

	    <p>Person:
        		<form:select path="person">
 				<form:options items="${people_list}" itemValue="id" itemLabel="fullName"/>
        		</form:select>
	    </p>
	    <p>State:
	    		<form:select path="state">
            		<form:option value="WA"/>
            		<form:option value="OR"/>
            		<form:option value="CA"/>
        		</form:select>
        	</p>
        	<p>
        		<form:label path="expiration_date">Expiration Date
		    <form:errors path="expiration_date"/>
		    <form:input type="date" min="${min_date}" required="true" path="expiration_date"/></form:label>
        	
        	</p>
	    <p><input type="submit" value="Create"/></p>

 	</form:form>
	

</body>
</html>