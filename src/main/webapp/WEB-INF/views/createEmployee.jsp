<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>Create Employee</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel = "stylesheet" type = "text/css"  href = "app.css" />
      </title>
   </head>
   <body>
       <c:if test="${updatemsg == null}">
       <c:set var="route" value="add" />
       </c:if>
       <c:if test="${updatemsg == 1}">
       <c:set var="route" value="Update" />
       </c:if> 
       <form:form id ="createForm" commandName="employee" action="${route}" method="post">
       <div class = "row">
            <div class ="col-sm-2">
            </div>
            <div class ="col-sm-4">
            <h3>Employee Details:</h3>
            <br>
            <form:input path ="id" type="hidden"/>
            </div>
       </div>
       <div class = "row">
             <div class ="col-sm-1">
             </div>
             <div class ="col-sm-4">
             <form:label path="name">Name</form:label>
             </div>
             <div class ="col-sm-4">
             <form:input path="name" type="text" placeholder="Name:" 
             pattern ="^[^-\s][a-zA-Z0-9_\s-]+$" required="required"  maxlength ="40"/>
             </div>
       </div>
       <div class = "row">
            <div class ="col-sm-1">
             </div>
            <div class ="col-sm-4">
            <form:label path="DOB">DOB</form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path ="DOB" type="date" placeholder="Birth Date" 
             min="1950-12-31" id = "DOB" required="required" />
            </div>
       </div>
       <div class = "row">
             <div class ="col-sm-1">
             </div>
            <div class ="col-sm-4">
            <form:label path="dateOfJoining">DOJ</form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path= "dateOfJoining" type="date" 
            placeholder= "DOJ" min ="1950-12-31"  required="required"  id ="DOJ"/>
            </div>
       </div>
       <div class = "row">
            <div class ="col-sm-1">
             </div>
            <div class ="col-sm-4">
            <form:label path="phoneNo">PhoneNo</form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path="phoneNo" type="number"  placeholder= "Phone No" min ="0" 
            max ="10000000000"  required="required" />
            </div>
       </div>
       <div class = "row">
             <div class ="col-sm-1">
             </div>
            <div class ="col-sm-4">
            <form:label path="emailId">Email ID</form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path= "emailId" type="email"  placeholder="Email"  
            required="required"/>
            </div>
       </div>
       <div class = "row">
            <div class ="col-sm-1">
            </div>
            <div class ="col-sm-4">
            <form:label path="rating">Rating</form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path ="rating" type="number" placeholder="Rating(/5)" 
             required="required" min="1" max="5"/>
            </div>
       </div>
       <div class = "row">
            <div class ="col-sm-1">
             </div>
            <div class ="col-sm-4">
            <form:label path="salary">Salary </form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path ="salary" type="number"  placeholder="Salary"  
             min ="0" max ="100000000" required="required"/>
            </div>
       </div>
       <div class = "row">
            <div class ="col-sm-1">
            </div>
            <div class ="col-sm-4">
            <form:label path="designation">Designation </form:label>
            </div>
            <div class ="col-sm-4">
            <form:input path ="designation" type="text"  pattern ="^[a-zA-Z ]+$" 
             placeholder="Designation" required="required" maxlength = "30"/>
            </div>
       </div>
       <br>
       <div class = "row">
       <c:forEach items="${employee.addresses}" var = "address" varStatus="vs">
            <c:if test= "${address.type == 'ep'}">  
            <div class ="col-sm-6">
            <p><i class="fa fa-address-card" aria-hidden="true"></i> &nbsp; Permanent Address:</p>
            <div class ="row">
            <div class ="col-sm-4"> 
            <form:input path="addresses[${vs.index}].addressLineOne" type="text" class = "address" 
            placeholder="Line1" maxlength = "100" required="required"/>
            </div>
            </div>
            <br>
            <div class ="row">
            <div class ="col-sm-4"><form:input path="addresses[${vs.index}].addressLineTwo"  type="text" 
            class = "address"  placeholder="Line2" maxlength = "100" required="required" /></div>
            </div>
            <br>
            <div class ="row">
            <div class ="col-sm-4"><form:input path="addresses[${vs.index}].city" type="text"  
            class = "address" placeholder="City" maxlength = "100"  required="required" /></div>
            </div>   
            <form:input path="addresses[${vs.index}].type" type="hidden"/>
            <form:input path="addresses[${vs.index}].status" type="hidden"/>  
            </div>
            </c:if>
            <c:if test= "${address.type == 'eo'}">
            <div class ="col-sm-6">
            <p><i class="fa fa-address-card" aria-hidden="true"></i> &nbsp; Present Address:</p>
            <div class ="row">
            <div class ="col-sm-4"><form:input path="addresses[${vs.index}].addressLineOne" 
            type="text" class = "address" maxlength = "100" 
            placeholder="Line1" required="required"/>
            </div>
            </div>
            <br>
            <div class ="row">
            <div class ="col-sm-4"><form:input path="addresses[${vs.index}].addressLineTwo" 
            type="text" class = "address" placeholder="Line2" 
            maxlength = "100"  required="required"/></div>
            </div>
            <br>
            <div class ="row">
            <div class ="col-sm-4"><form:input path="addresses[${vs.index}].city" 
            type="text" class = "address" placeholder="City" 
            maxlength = "100" required="required"/></div>
            </div> 
            <form:input path="addresses[${vs.index}].type" type="hidden"/>
            <form:input path="addresses[${vs.index}].status" type="hidden"/>   
            </div> 
            </c:if>   
       </c:forEach> 
       </div>
       <br>
       <c:if test="${updatemsg == null}">
       <div class ="row">
           <div class ="col-sm-4">
           </div>
           <div class ="col-sm-6">
           <form:button type="submit" class= "btn btn-primary">
           <i class="fa fa-plus"></i>Add</form:button> 
           </div>
       </div>
       </c:if>
      <c:if test="${updatemsg == 1}">
       <div class = "row">
            <div class ="col-sm-4">
            </div>
            <div class ="col-sm-4">
            <form:button type="submit" class= "btn btn-primary" 
             onclick="return confirm('Are you sure you want to Update?Employee id:${employee.id}')">
            <i class="fa fa-edit"></i>Update</form:button> 
            <form:button type="submit" class= "btn btn-danger" 
                         onclick= "this.form.action='display';this.form.method='get'">
            <i class="fa fa-trash"></i>Cancel</form:button> 
       </div>
       </div> 
     </c:if>
    </form:form>
    </body>
    <script src="./footer.js"></script> 
    <script> 
     <c:if test= "${id > 0}">
     myAlert("Your details are Added EmployeeId : "+<c:out value = "${id}"/>);  
     </c:if>
     <c:if test= "${id == 0}">
     MyAlert("your details are not Added"); 
     </c:if>  
     <c:if test= "${id == -1}">
     MyAlert("Sorry!...Connection Error..."); 
     </c:if>  
     document.getElementById("DOB").max = new Date(new Date().getTime() - new Date().getTimezoneOffset() *   
     60000).toISOString().split("T")[0];
     document.getElementById("DOJ").max = new Date(new Date().getTime() - new Date().getTimezoneOffset() *   
     60000).toISOString().split("T")[0];
    </script>
</html>


