<!DOCTYPE html>
<html>
   <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
   <title>HR Management tool</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <link rel ="stylesheet" type = "text/css"  href = "app.css" />
   <body>
      <div class="navbar">
         <div class = col-sm-2>
         <br>
         &nbsp;
         <img  src="https://www.ideas2it.com/wp-content/themes/ideas2it-theme/assets/images/logo_web_white.png"/>
         </div>
         <div class = "col-sm-3"> 
            <a href= "employeeIndex.jsp" class="navlinks" onclick="openEvent(emp)" id ="emp"><img src="https://lh3.googleusercontent.com/-0345jk-Nn8U/W36AgSqCixI/AAAAAAAAAAY/JWtt_ufwKegyPtRxeVf0BJnr2O-VHEopwCL0BGAYYCw/h49/Layer%2B0%2B%25281%2529.png"></i> Employee Management</a>
         </div>
         <div class = "col-sm-3">  
            <a  href= "projectIndex.jsp" class="navlinks" onclick="openEvent(pro)" id = "pro"><img src="https://lh3.googleusercontent.com/-vkMY3_yv1_M/W36EqRZEqxI/AAAAAAAAABc/cprBtxMvAAstvjT_ASD5oQpkq-ji2t2cACL0BGAYYCw/h50/1.png"></i> Project Management </a> 
         </div>
         <div class = "col-sm-3"> 
            <a  href= "clientIndex.jsp" class="navlinks" onclick="openEvent(cli)"  id = "cli" ><img src="https://lh3.googleusercontent.com/-ANRMRrc-uIs/W36BwYYrbbI/AAAAAAAAABE/UFaiGgpL0lQrnSP9HAxSeJUWQCj0kMTFwCL0BGAYYCw/h42/Layer%2B0%2B%25283%2529.png"> Client Management</a> 
         </div>
         <div class = "col-sm-1">
            <br>
            <form class="form" action="UserController" method = "post">
               <button type="submit" class="btn btn-default btn-sm" 
                  name = "operation" value ="logout" onclick="return confirm('Are you sure you want to Logout?')">
               <span class="glyphicon glyphicon-log-out"></span> Log out
               </button>
            </form>
         </div>
      </div>
   </body>
</html>


