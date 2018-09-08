<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
     <title>display Clients</title>
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">   
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> 
     <link rel ="stylesheet" type = "text/css"  href = "app.css" />
     <style>
     .content {
        width: auto;
        height: auto; 
        display: none;
      }
     </style>
</head>
<body>
       <br> 
      <div class = "row">
           <div class ="col-sm-2">
           </div>
           <div class ="col-sm-4"> 
           <form action ="displayClient" method="get"> 
           <button class="btn btn-success" type = "submit"> <i class="fa fa-refresh" aria-hidden="true"></i> Refresh</button>
           </form>
      </div>
      <div class ="col-sm-4">
           <form action ="searchClient" method="post"> 
           <input class = "searchBar" type="number"  placeholder="Search.." name ="id" max= "100000000">
           <button type="submit"  class="btn btn-info" name ="operation" value ="search" ><i class="fa fa-search"></i> Search</button>
           </form>
      </div>
      </div>
     <c:if test= "${clients.size() == 0 }">
       <div class ="row">
       <div class ="col-sm-2">
       </div> 
       <div class ="col-sm-4"> 
       <img src = "https://lh3.googleusercontent.com/-PTH5AmaGw6o/W5DCxX6xZ9I/AAAAAAAAACE/kDeOnh2-sRwncQh3cvs94UteY8-id2K6QCL0BGAYYCw/h450/Layer%2B0.png"/>
       </div>
       </div>
   </c:if>
      <br>
         <div style="overflow-x:auto;" class="w3-container">
            <c:choose>
               <c:when test="${clients != null}">
                  <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id = "resizable">
                     <thead>
                        <tr class = "ui-widget-header">
                           <th>ID</th>
                           <th>Name</th>
                           <th>Requirement</th>
                           <th>Action</th>
                           <th>Action</th>
                           <th>Action</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${clients}" var="client">
                           <tr class>
                              <td class = "id" >${client.id}</td>
                              <td class = "name" >${client.name}</td>
                              <td class = "requirement" >${client.requirement}</td>
                              <td> <button type ="button" 
                    class="w3-button w3-white w3-border w3-border-red w3-round-large view">View</button>
                              </td>
                              <form action ="deleteClient" method="post">
                               <input type="hidden" value ="${client.id}" name ="deleteId">
                               <td><button class="btn btn-danger" type = "submit"
                               onclick="return confirm('Are you sure you want to delete?Project id:${project.id}')">
                               <i class="fa fa-trash"></i> Delete</button></td>
                               </form>
                               <form action ="getClient" method="post">
                               <input type="hidden" value ="${client.id}" name ="updateId">  
                               <td><button class="btn btn-success" type="submit"/><i class="fa fa-edit"></i> Update</button></td>
                               </form>
                           </tr>
                         
                           <tr class ="content">
                               <td colspan ="6">
                                 <div style="overflow-x:auto;" class="w3-container">
                                 <c:choose>
                                    <c:when test="${client.projects != null}">
                                       <h5>Associated Projects</h5>
                                       <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" 
                                         id="choose-address-table">
                                          <thead>
                                             <tr class = "ui-widget-header">
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Starting Date</th>
                                                <th>Ending Date</th>
                                             </tr>
                                          </thead>
                                          <tbody>
                                             <c:forEach items="${client.projects}" var="project">
                                                <tr>
                                                   <td class = "id" >${project.id}</td>
                                                   <td class = "name" >${project.name}</td>
                                                   <td class = "startDate" >${project.startDate}</td>
                                                   <td class ="endDate" >${project.endDate}</td>
                                                </tr>
                                             </c:forEach>
                                          </tbody>
                                       </table>
                                    </c:when>
                                 </c:choose>
                              <div>
                           </td>
                       </tr> 
                   </c:forEach>
              </tbody>
      </table>
      <c:choose> 
      <c:when test="${not empty isclientsFound}">
      <div class ="middle">Sorry! No Clients found</div>
      </c:when>
      </c:choose>
      </c:when>
      </c:choose>
      </div>
      </form>
      </body>
      <script src="https://code.jquery.com/jquery-1.10.2.js"></script>  
      <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
      <script src="./footer.js"></script>
      <script> 
      <c:choose>      
      <c:when test= "${id == 0}">
      MyAlert("sorry...details Not added"); 
      </c:when>
  
      <c:when test="${deletemsg == 1}">
      myAlert("omg..your details are deleted"); 
      </c:when>

      <c:when test="${deletemsg == 0}">
      myAlert("sorry.....your details Not deleted"); 
      </c:when>

      <c:when test="${deletemsg == -1}">
      myAlert("Error Connecting....."); 
      </c:when>

      <c:when test ="${updatemsg == 1}">
      myAlert("your details are updated"); 
      </c:when>

      <c:when test="${updatemsg == 0}">
      myAlert("sorry.....your details Not updated"); 
      </c:when>

      <c:when test="${updatemsg == -1}">
      myAlert("Error Connecting....."); 
      </c:when>
     
      <c:when test ="${update == 1}">
      myAlert("your details are Updated"); 
      </c:when>

      <c:when test="${update == 0}">
      myAlert("sorry.....your details Not upadted"); 
      </c:when>

      <c:when test="${update == -1}">
      myAlert("Error Connecting....."); 
      </c:when>
      
      <c:when test="${search == 0}">
      myAlert("Not Found"); 
      </c:when>
  
      <c:when test="${search == -1}">
      myAlert("Error Connecting"); 
      </c:when>
   
      </c:choose>
   </script>   
</html>


