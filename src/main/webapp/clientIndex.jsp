<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
      <title>ClientIndex</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel ="stylesheet" type = "text/css"  href = "app.css" /> 
   </head>
   <body>
      <jsp:include page="header.jsp"/>
      <div class= "row">
         <div class="tab">
            <button class="tablinks" onclick="openEvent(event, 'display')" id="default">&ensp;  Display</button>
            <button class="tablinks" onclick="openEvent(event, 'add')"> &ensp; Add</button>
            <button class="tablinks" onclick="openEvent(event, 'restore')"> &ensp; Restore</button>
         </div>
         <div id="add" class="tabcontent">
            <iframe class ="frame" src="createClient"></iframe> 
              <%@ include file = "footer.jsp" %>
         </div>
         <div id="display" class="tabcontent">
            <iframe class ="frame" src="displayClient" ></iframe>
              <%@ include file = "footer.jsp" %>
         </div>
         <div id="restore" class="tabcontent">
            <iframe class ="frame" src="deletedClient" ></iframe>
              <%@ include file = "footer.jsp" %>
         </div>
        </div>
   </body>
   <script src="./footer.js"></script>
</html>


