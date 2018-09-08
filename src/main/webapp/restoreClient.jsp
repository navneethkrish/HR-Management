<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Restore Client</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel ="stylesheet" type = "text/css"  href = "app.css" />
 </head>
 <body>
     <form action="restoreClient" method="post">
     <br>
     <div class = "row">
         <div class ="col-sm-4">
         </div>
         <div class ="col-sm-8">
         <button class="btn btn-primary" onclick= "this.form.action='deletedClient';this.form.method='get'" type = "submit"> <i class="fa fa-refresh" aria-hidden="true"></i>Refresh</button>
         <c:if test= "${clients != null}">
             <button class="btn btn-success" type = "submit"> <i class="fa fa-refresh" aria-hidden="true"></i> Restore </button>
         </c:if> 
         </div>
     </div>
     <div class = "row">
          <div class ="col-sm-4">
          </div>
          <div class ="col-sm-8">
          </div>
     </div>
     <br>
     <div style="overflow-x:auto;" class="w3-container">
            <c:choose>
               <c:when test="${clients.size() != 0}">
                  <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id = "resizable">
                     <thead>
                        <tr class = "ui-widget-header">
                           <th>ID</th>
                           <th>Name</th>
                           <th>Requirement</th>
                           <th>Restore</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${clients}" var="client">
                           <tr class>
                              <td class = "id" >${client.id}</td>
                              <td class = "name" >${client.name}</td>
                              <td class = "requirement" >${client.requirement}</td>
                              <td><input type="checkbox" name="selected" value="${client.id}"></td>
                           </tr>
                        </c:forEach>
                   </tbody>
               </table>
             </c:when> 
          </c:choose>
      </div>
    <c:choose>
    <c:when test="${clients.size() == 0}">
       <div class ="row">
       <div class = col-sm-1>
       </div> 
        <div class = col-sm-6>  
       <img src = "https://lh3.googleusercontent.com/-PTH5AmaGw6o/W5DCxX6xZ9I/AAAAAAAAACE/kDeOnh2-sRwncQh3cvs94UteY8-id2K6QCL0BGAYYCw/h450/Layer%2B0.png"/>
       </div>
       </div> 
    </c:when>
    </c:choose>
    </div>   
    </form> 
    </div>
    </body> 
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>  
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
    <script src="./footer.js"></script>
    <script>  
     
   
      <c:if test ="${update == 1}">
      myAlert("your details are restored"); 
      </c:if>

      <c:if test="${update == 0}">
      myAlert("sorry.....your details Not restored"); 
      </c:if>

      <c:if test="${update == -1}">
      myAlert("Error Connecting....."); 
      </c:if>

    
   </script>  
</html>

