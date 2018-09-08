<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
      <title>displayEmployee</title>
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
   <div class = "row" > 
       <div class ="col-sm-2">
       </div>
       <div class ="col-sm-4">
       <form action ="display" method="get"> 
       <button class="btn btn-success" type = "submit"> <i class="fa fa-refresh" aria-hidden="true"></i>Refresh</button>
       </form>
       </div>
       <div class ="col-sm-4">
       <form action ="search" method="post">  
       <input class = "searchBar" type="number"  placeholder="Search.." name ="id" max= "100000000">
       <button type="submit"  class="btn btn-info"><i class="fa fa-search"></i> 
       Search</button>
       </form>
       </div>
   </div>
   <c:if test= "${employees.size() == 0 }">
       <div class ="row">
       <div class ="col-sm-2">
       </div> 
       <div class ="col-sm-4"> 
       <img src = "https://lh3.googleusercontent.com/-PTH5AmaGw6o/W5DCxX6xZ9I/AAAAAAAAACE/kDeOnh2-sRwncQh3cvs94UteY8-id2K6QCL0BGAYYCw/h450/Layer%2B0.png"/>
       </div>
       </div>
   </c:if>
   <div style="overflow-x:auto;" class="w3-container">
          <c:choose>
               <c:when test="${not empty employees}">
                  <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id = "resizable">
                     <thead>
                        <br>
                        <tr class = "ui-widget-header">
                           <th>ID</th>
                           <th>Name</th>
                           <th>Age</th>
                           <th>EmailId</th>
                           <th>DOB</th>
                           <th>Rating</th>
                           <th>Designation</th>
                           <th>DOJ</th>
                           <th>Salary</th>
                           <th>Action</th>
                           <th>Action</th>
                           <th>Action</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${employees}" var="employee">
                         <tr>
                              <td class = "id" >${employee.id}</td>
                              <td class = "name" >${employee.name}</td>
                              <td class = "age" >${employee.age}</td>
                              <td class = "emailId">${employee.emailId}</td>
                              <td class = "DOB">${employee.DOB}</td>
                              <td class ="rating">${employee.rating}</td>
                              <td class ="designation">${employee.designation}</td>
                              <td class = "DOJ">${employee.dateOfJoining}</td>
                              <td class = "salary">${employee.salary}</td>
                              <td> <button type ="button" 
                                 class="w3-button w3-white w3-border w3-border-red w3-round-large view">Address</button>
                              </td>
                              <td>
                              <form action ="delete" method="post">
                              <input type="hidden" value ="${employee.id}" name ="deleteId">
                              <button class="btn btn-danger" type = "submit"
                              value="delete" 
                              onclick="return confirm('Are you sure you want to delete?Employee id:${employee.id}')">
                              <i class="fa fa-trash"></i> Delete</button>
                              </form>
                              </td>
                              <td>
                              <form action ="update" method="post">
                              <input type="hidden" value ="${employee.id}" name ="updateId">  
                              <button class="btn btn-success" type="submit"/><i class="fa fa-edit"></i> Update</button>
                              </form>
                              </td>
                              </tr>
                              <tr class= "content">
                              <td colspan="6">
                              <c:forEach items="${employee.addresses}" var="address"> 
                              <c:if test = "${address.type == 'ep'}"> 
                              <h6>Permanent Address:</h6>
                              <p>${address.addressLineOne}</p>  
                              <p>${address.addressLineTwo}</p> 
                              <p>${address.city}</p>  
                              </c:if> 
                              </c:forEach>
                              </td> 
                              <td colspan="6">
                              <c:forEach items="${employee.addresses}" var="address">
                              <c:if test = "${address.type == 'eo'}"> 
                              <h6>Personal Address:</h6>
                              <p>${address.addressLineOne}</p>  
                              <p>${address.addressLineTwo}</p> 
                              <p>${address.city}</p>  
                              </c:if> 
                              </c:forEach>
                             </td> 
                           <tr> 
                         </c:forEach> 
                     </tbody>
                  </table>
               </c:when> 
            </c:choose>
      </div>   
      </form> 
      </div>
<div class="bubbles-wrapper bubbles-vertical">
  <div class="bubble"></div>
  <div class="bubble"></div>
  <div class="bubble"></div>
  <div class="bubble"></div>
  <div class="bubble"></div>
</div>

<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
  <defs>
    <filter id="goo">
      <feGaussianBlur in="SourceGraphic" stdDeviation="10" result="blur" />
      <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 18 -7" result="goo" />
      <feBlend in="SourceGraphic" in2="goo" />
  	</filter>
  </defs>
</svg>
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


