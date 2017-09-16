<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
 --> 

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/static/css/style.css'/>">
 
 <title>Ninja Gold</title>
</head>
<body>
    <div id="wrapper">
      <div id="you_gold">
        <p>Your Gold: <span id="goldbar"><c:out value="${gold}"/></span></p>
      </div>
      <div id="building_block">
        <div class="building">
          <h2>Farm</h2>
          <p>(earns 10-20 gold)</p>
          <form action='/process_money' method = 'POST'>
            <input type='hidden' name='building' value='farm'>
            <input type='submit' value='Find Gold!'>
          </form>
        </div>
        <div class="building">
          <h2>Cave</h2>
          <p>(earns 5-10 gold)</p>
          <form action='/process_money' method = 'POST'>
            <input type='hidden' name='building' value='cave'>
            <input type='submit' value='Find Gold!'>
          </form>
        </div>
        <div class="building">
          <h2>House</h2>
          <p>(earn 2-5 gold)</p>
          <form action='/process_money' method='POST'>
            <input type='hidden' name='building' value='house'>
            <input type='submit' value='Find Gold!'>
          </form>
        </div>
        <div class="building">
          <h2>Casino</h2>
          <p>(earns/loses 0-50 gold)</p>
          <form action='/process_money' method='POST'>
            <input type='hidden' name='building' value='casino'>
            <input type='submit' value='Find Gold!'>
          </form>
        </div>
      </div>
      <div>
        <p>Activities:</p>
        <div id="activities">    		
        		<c:forEach var="x" items="${results}">
         			<c:out value="${x}" escapeXml="false"/></p>
	 		</c:forEach>	 		
         </div>
      </div>
<!--       	<p><a href="/clearSession">Clear Session</a></p>
 -->      
      <form action='/clearSession'>
        <input type='submit' value='RESET GAME'>
      </form>
    </div>
</body>
</html>