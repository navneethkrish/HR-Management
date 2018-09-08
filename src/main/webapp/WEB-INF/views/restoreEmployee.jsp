<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>displayEmployee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel ="stylesheet" type = "text/css"  href = "app.css" />
 </head>
 <body>
     <form action="restoreEmployee" method="post">
     <br>
     <div class = "row">
         <div class ="col-sm-4">
         </div>
         <div class ="col-sm-8">
         <button class="btn btn-primary" onclick= "this.form.action='restore';this.form.method='get'" type = "submit"> <i class="fa fa-refresh" aria-hidden="true"></i>Refresh</button>
         <c:if test= "${employees != null}">
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
          <c:when test="${not empty employees}">
            <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id="choose-address-table">
              <thead>
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
                  <th>Restore</th>
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
                  <td><input type="checkbox" name="selected" value="${employee.id}"></td>
                  </c:forEach>
    </tr>
    </tbody>
    </table> 
    </c:when> 
    <c:when test="${employees.size() == 0}">
     <img src = "https://lh3.googleusercontent.com/-PTH5AmaGw6o/W5DCxX6xZ9I/AAAAAAAAACE/kDeOnh2-sRwncQh3cvs94UteY8-id2K6QCL0BGAYYCw/h450/Layer%2B0.png"/>
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

