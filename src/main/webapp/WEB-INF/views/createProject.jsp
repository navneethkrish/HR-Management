<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Create Project</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel = "stylesheet" type = "text/css"  href = "app.css" />
    </title>
  </head>
<body>
    <c:if test="${updatemsg == null}">
        <c:set var="route" value="addProject"/>
        </c:if>
        <c:if test="${updatemsg == 1}">
        <c:set var="route" value="updateProject"/>
     </c:if> 
     <form:form commandName="project" action="${route}" method="post">
     <div class = "row">
         <div class ="col-sm-2">
         </div> 
         <div class ="col-sm-2">
         </div>
         <div class ="col-sm-4">
         <h4>Project Details:</h4>
         <form:input path="id" type="hidden"/> 
         </div>
     </div>
     <div class = "row">
         <div class ="col-sm-2">
         </div>
         <div class ="col-sm-4">
         <p>Project Name:</p>
         </div>
         <div class ="col-sm-4">
         <form:input path="name" type="text" placeholder="Name:" 
         pattern ="^[^-\s][a-zA-Z0-9_\s-]+$" required = "required" max-length = "40"/>
         </div>
     </div>
     <div class = "row">
         <div class ="col-sm-2">
         </div>  
         <div class ="col-sm-4">
         <p> Starting Date:</p>
         </div>
         <div class ="col-sm-4">
         <form:input path="startDate" type="date" placeholder= "Starting Date" min="1950-12-31" max ="2050-12-31"
          required = "required"/>
         </div>
     </div>
     <div class = "row">
         <div class ="col-sm-2">
          </div>
         <div class ="col-sm-4">
         <p>Ending Date:</p>
         </div>
         <div class ="col-sm-4">
         <form:input path="endDate" type="date" placeholder= "Ending Date" min ="1950-12-31" max ="2050-12-31" 
         required= "required" />
         </div>
     </div>
     <br>
     <c:if test="${associatedEmployees != null && employees != null}">
     <div class = "row">
         <div class = "col-sm-9"> 
         </div> 
         <div class = "col-sm-3"> 
         <input id="myInput" type="text" placeholder="Search..">
         </div>
     </div>
     </c:if>
     <div style="overflow-x:auto;" class="w3-container">
     <c:choose>
         <c:when test="${not empty associatedEmployees}">
         <div class = "row">
          <div class = "col-sm-4"> 
          </div>
          <div class = "col-sm-8"> 
          <h4>Associated Employees</h4>
          </div>          
          </div>   
         <br>
         <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id="choose-address-table">
           <thead>
             <tr class = "ui-widget-header">
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Date Of Birth</th>
                <th>Employee Rating</th>
                <th>Date of Joining</th>
                <th>Salary</th>
                <th>add/delete</th>
             </tr>
           </thead>
         <tbody id = "search">
             <c:forEach items="${associatedEmployees}" var="employee">
                <tr>
                  <td class = "id" >${employee.id}</td>
                  <td class = "name" >${employee.name}</td>
                  <td class = "age" >${employee.age}</td>
                  <td class = "emailId">${employee.emailId}</td>
                  <td class = "DOB">${employee.DOB}</td>
                  <td class ="rating">${employee.rating}</td>
                  <td class = "DOJ">${employee.dateOfJoining}</td>
                  <td class = "salary">${employee.salary}</td>
                  <td><input type="checkbox" name="select" value="${employee.id}" checked><br></td>
                </tr>
             </c:forEach>
         </tbody>
       </table>
      </c:when>
     </c:choose>
     <br>
      <c:choose>
        <c:when test="${not empty employees}">
           <div class = "row">
          <div class = "col-sm-4"> 
          </div>
          <div class = "col-sm-8"> 
          <h4>All Employees</h4>
          </div>          
          </div>   
         <br>
          <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id="choose-address-table">
            <thead>
              <tr class = "ui-widget-header">
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Date Of Birth</th>
                <th>Employee Rating</th>
                <th>Date of Joining</th>
                <th>Salary</th>
                <th>add/delete</th>
              </tr>
            </thead>
            <tbody id = "search">
              <c:forEach items="${employees}" var="employee">
                <tr>
                  <td class = "id" >${employee.id}</td>
                  <td class = "name" >${employee.name}</td>
                  <td class = "age" >${employee.age}</td>
                  <td class = "emailId">${employee.emailId}</td>
                  <td class = "DOB">${employee.DOB}</td>
                  <td class ="rating">${employee.rating}</td>
                  <td class = "DOJ">${employee.dateOfJoining}</td>
                  <td class = "salary">${employee.salary}</td>
                  <td><input type="checkbox" name="selected" value="${employee.id}"><br></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
      </c:choose>
      <c:if test="${updatemsg == null}">
      <br>
      <div class ="row">
          <div class ="col-sm-4">
          </div>
          <div class ="col-sm-8">
          <form:button type="submit" class= "btn btn-primary"><i class="fa fa-plus"></i>&nbsp;Add</form:button> 
          </div>
      </div>
      </c:if>
      <c:if test="${updatemsg == 1}">
      <br> 
      <div class = "row">
          <div class ="col-sm-4">
          </div>
          <div class ="col-sm-6">
          <form:button type="submit"  class= "btn btn-primary" onclick="return confirm('Are you sure you want to Update?Project id:${project.id}')"> <i class="fa fa-edit"></i>&nbsp;Update</form:button>
          <form:button class="btn btn-danger" type = "submit" onclick= "this.form.action='displayProject';this.form.method='get'">
                  <i class="fa fa-trash"></i>Cancel</form:button>
          </div>
      </div>
      </c:if>
      </form:form> 
      </body>
      <script src="https://code.jquery.com/jquery-3.3.1.min.js"
			integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
			crossorigin="anonymous">
       </script>
       <script src="./footer.js"></script>
       <script> 
             <c:if test= "${id > 0}">
             myAlert("Your details are Added:ProjectId : "+<c:out value = "${id}"/>);  
             </c:if>
             <c:if test= "${id == -1}">
             MyAlert("Sorry!...Connection Error..."); 
             </c:if>
             <c:if test= "${id == 0}">
             MyAlert("sorry...details Not added"); 
             </c:if>
             <c:choose>
             <c:when test ="${update == 1}">
             MyAlert("your details are Updated"); 
             </c:when>
             <c:when test="${update == 0}">
             MyAlert("sorry.....your details not upadted"); 
             </c:when>
             <c:when test="${update == -1}">
             MyAlert("Sorry !....Error Connecting....."); 
             </c:when>
             </c:choose>
       </script>
</html>


