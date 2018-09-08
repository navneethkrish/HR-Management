<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
      <title>EmployeeIndex</title>
      <jsp:include page="header.jsp"/> 
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel ="stylesheet" type = "text/css"  href = "app.css" />
  </head>
  <body>
          <div class= "row">
          <div class="tab">
          <button type ="submit" class="tablinks" onclick="openEvent(event, 'display')" id ="default">
&ensp;Display</button>
          <button class="tablinks" onclick="openEvent(event, 'add')"> &ensp;Add</button>
         
          <button class="tablinks" onclick="openEvent(event, 'restore')"> &ensp;Restore</button> 
         </div>
         <div id="display" class="tabcontent">
            <iframe class = "frame" src="display"></iframe>
            <%@ include file = "footer.jsp" %>
         </div>
         <div id="add" class="tabcontent">
            <iframe class ="frame" src="create"></iframe> 
             <%@ include file = "footer.jsp" %> 
         </div>
         <div id="restore" class="tabcontent">
            <iframe class ="frame" src="restore"></iframe> 
             <%@ include file = "footer.jsp" %> 
         </div>      
         </div>
  </body>
    <script src="./footer.js"></script>
</html>


