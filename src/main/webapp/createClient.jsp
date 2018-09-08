<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>Create Client</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" type = "text/css"  href = "app.css" />
      </title>
   </head>
   <body>
        <c:if test="${updatemsg == null}">
        <c:set var="route" value="addClient"/>
        </c:if>
        <c:if test="${updatemsg == 1}">
        <c:set var="route" value="updateClient"/>
        </c:if> 
        <form:form commandName="client" action="${route}" method="post">
         <div class = "row">
             <div class ="col-sm-1">
             </div>
             <div class ="col-sm-2">
             </div>
             <div class ="col-sm-4">
             <h4>Client Details:</h4>
             </div>
         </div>
             <form:input path="id" type="hidden"/>
         <div class = "row">
             <div class ="col-sm-1">
             </div>
             <div class ="col-sm-4">
             <p>Name:</p>
             </div>
             <div class ="col-sm-4">
             <form:input path="name" type="text" placeholder="Name:" required = "required"   
                 pattern ="^[^-\s][a-zA-Z0-9_\s-]+$"  maxlength = "30"/>
             </div>
         </div>
         <div class = "row">
             <div class ="col-sm-1">
             </div>
             <div class ="col-sm-4">
             <p> Requirement:</p>
             </div>
             <div class ="col-sm-4">
             <form:input path="requirement" type="text" placeholder="Requirement:"
                 required="required" maxlength = "250"/>
             </div>
         </div>
         <div class = "row">
             <div class ="col-sm-1">
             </div> 
             <div class ="col-sm-4">
             <p> PhoneNo:</p>
             </div>
             <div class ="col-sm-4">
             <form:input path="PhoneNo" type="number"  placeholder="PhoneNo:" max ="10000000000"
             required="required"/>
             </div>
         </div> 
      <br>
    <div class = "row">
       <c:forEach items="${client.addresses}" var = "address" varStatus="vs">
            <c:if test= "${address.type == 'cp'}">  
            <div class ="col-sm-6">
            <p><i class="fa fa-address-card" aria-hidden="true"></i>Permanent Address:</p>
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
            <c:if test= "${address.type == 'co'}">
            <div class ="col-sm-6">
            <p><i class="fa fa-address-card" aria-hidden="true"></i>Present Address:</p>
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
           <button type="submit" value="Add" class= "btn btn-primary" name = "operation"><i class="fa fa-plus"></i>&nbsp;Add</button> 
           </div>
           </div>
        </c:if>
        </div>
        <c:if test="${associatedProjects != null && projects != null}">
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
          <c:when test="${not empty associatedProjects}">
          <div class = "row">
          <div class = "col-sm-4"> 
          </div>
          <div class = "col-sm-8"> 
          <h4>Associated Projects</h4>
          </div>          
          </div> 
           <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id="choose-address-table">
                  <thead>
                     <tr class = "ui-widget-header">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Starting Date</th>
                        <th>Ending Date</th>
                        <th>Add/delete</th>
                     </tr>
                  </thead>
                  <tbody id="search">
                     <c:forEach items="${associatedProjects}" var="project">
                        <tr>
                           <td class = "id" >${project.id}</td>
                           <td class = "name" >${project.name}</td>
                           <td class = "startDate" >${project.startDate}</td>
                           <td class ="endDate" >${project.endDate}</td>
                           <td><input type="checkbox" name="selected" checked value="${project.id}"></td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:when>
         </c:choose>
       </div>
       <br>  
       <div style="overflow-x:auto;" class="w3-container">
         <c:choose>
            <c:when test="${not empty projects}">
               
              <div class = "row">
              <div class = "col-sm-4"> 
              </div>
              <div class = "col-sm-8"> 
              <h4>All Projects</h4>
              </div>          
              </div> 
               <table class="w3-table-all w3-hoverable ui-widget ui-widget-content" id="choose-address-table">
                  <thead>
                     <tr class = "ui-widget-header">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Starting Date</th>
                        <th>Ending Date</th>
                        <th>Add/delete</th>
                     </tr>
                  </thead>
                  <tbody id = "search">
                     <c:forEach items="${projects}" var="project">
                        <tr>
                           <td class = "id" >${project.id}</td>
                           <td class = "name" >${project.name}</td>
                           <td class = "startDate" >${project.startDate}</td>
                           <td class ="endDate" >${project.endDate}</td>
                           <td><input type="checkbox" name="unselect" value="${project.id}"></td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:when>
         </c:choose>
         </div>
        <br>
         <c:if test="${updatemsg == 1}">
             <div class ="row">
             </div>
             <div class ="row">
             <div class ="col-sm-4">
             </div>
             <div class ="col-sm-8">
            <form:button type="submit"  class= "btn btn-primary"  onclick="return confirm('Are you sure you want to Update?Client id:${client.id}')"> <i class="fa fa-edit"></i>&nbsp;Update</form:button>
            <form:button class="btn btn-danger" type = "submit" 
            onclick= "this.form.action='displayClient';this.form.method='get'">
            <i class="fa fa-trash"></i>&nbsp;Cancel</form:button>
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
            <c:set var = "id" scope = "session" value = "${Id}"/>
             <c:if test= "${id > 0}">
             myAlert("Your details are Added:ClientId"+<c:out value = "${id}"/>);  
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


