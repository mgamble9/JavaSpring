<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dojo Survey Index</title>
</head>
<body>
	
	<form method="POST" action="/login">
	    <p><label>Username: <input type="text" name="name" required autofocus></label></p>
	    <p><label>Dojo Location: <select name="location">
		    		<option value="Seattle">Seattle</option>
		    		<option value="Dallas">Dallas</option>
		    		<option value="Irvine">Irvine</option>
		    		</select>
			</label></p>
	    <p><label>Favorite Language: <select name="language">
		    		<option value="Python">Python</option>
		    		<option value="Java">Java</option>
		    		<option value="Javascript">Javascript</option>
		    		</select>
			</label>	</p>	
	    <p><label>Comment (optional): <textarea name="comment" rows="4" cols="50" placeholder="optional"></textarea>
	    		</label></p>		
	    <p><button>Submit</button></p>
	</form>
 
</body>
</html>