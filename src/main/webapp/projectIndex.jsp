<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>ProjectIndex</title>
     <jsp:include page="header.jsp"/>
  </head>
  <body>
   <div class= "row">
      <div class="tab">
        <button class="tablinks" onclick="openEvent(event, 'display')" id="default">&ensp; Display </button>
        <button class="tablinks" onclick="openEvent(event, 'add')"> &ensp; Add </button>
      </div>
      <div id="add" class="tabcontent">
        <iframe class ="frame" src="createProject"></iframe> 
        <%@ include file = "footer.jsp" %>
      </div>
      <div id="display" class="tabcontent">
        <iframe class ="frame" src="displayProject"></iframe>
        </form>
        <%@ include file = "footer.jsp" %>
      </div>
    </div>
  </body>
 <script src="./footer.js"></script>
</html>


