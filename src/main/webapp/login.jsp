<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"> 
      <title>login</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
 </head>
 <style>
 .box {
    width: 500px;
    margin: 200px 0;
}

.shape1{
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #032d60;
    border-radius: 80px;
    float: left;
    margin-right: -50px;
}
.shape2 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #032d60;
    border-radius: 80px;
    margin-top: -30px;
    float: left;
}
.shape3 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #032d60;
    border-radius: 80px;
    margin-top: -30px;
    float: left;
    margin-left: -31px;
}
.shape4 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #032d60;
    border-radius: 80px;
    margin-top: -25px;
    float: left;
    margin-left: -32px;
}
.shape5 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #0074d9;
    border-radius: 80px;
    float: left;
    margin-right: -48px;
    margin-left: -32px;
    margin-top: -30px;
}
.shape6 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #0074d9;
    border-radius: 80px;
    float: left;
    margin-right: -20px;
    margin-top: -35px;
}
.shape7 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #0074d9;
    border-radius: 80px;
    float: left;
    margin-right: -20px;
    margin-top: -57px;
}
.shape8 {
    position: relative;
    height: 150px;
    width: 150px;
    background-color: #0074d9;
    border-radius: 80px;
    float: left;
    margin-right: -10px;
    margin-top: -57px;
}

.float {
    position: absolute;
    z-index: 2;
}

.form {
    margin-left: 145px;
}

.repeat {
    display:none;
} 

</style>
<body>
<div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div class="box">
                    <div class="shape1"></div>
                    <div class="shape2"></div>
                    <div class="shape3"></div>
                    <div class="shape4"></div>
                    <div class="shape5"></div>
                    <div class="shape6"></div>
                    <div class="shape7"></div>
                    <div class="float">
                        <form class="form" action="UserController" method = "post">
                            <div class="form-group">
                                <label for="username" class="text-white">Username:</label><br>
                                <input type="email" name="email" placeholder = "email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-white">Password:</label><br>
                                <input id="10" type="password" name="password"  placeholder ="password" class="form-control" required>
                            </div>
                            <div class="form-group repeat" id = "5">
                                <label for="password" class="text-white">Repeat Password:</label><br>
                                <input type="password" name="repassword" class="form-control" placeholder ="password" id ="12">
                            </div>
                            <div class="form-group repeat" id = "11">
                                 <p style ="color:red" id = "14">password not Matched....<p>
                            </div>
                            <div class="form-group row">
                                <div class ="col-sm-1">
                                </div> 
                                <div class = "col-sm-4"> 
                                <input type="submit" name="operation" class="btn btn-success btn-md" value="login" id = "1">
                                </div>
                                <div class = "col-sm-4"> 
                                <input type="button" name="operation" class="btn btn-primary btn-md" id ="2" value="SignUp">
                                </div>
                                <div class = "col-sm-4"> 
                                <input type="button" name="operation" class="btn btn-success btn-md repeat" value="login" id = "3">
                                </div>
                                <div class = "col-sm-4"> 
                               
                                <input type="submit" name="operation" class="btn btn-primary btn-md repeat" value="SignUp" id ="4">
                                 
                                </div>   
                            </div>
                         </form>
                      </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
      $(document).ready(function(){
     $("#2").click(function(){
     $("#1").hide();
     $("#2").hide();
     $("#3").show();
     $("#5").show();
     $("#14").show();  
     $("#5").val("");
     });
     });

     $(document).ready(function(){
     $("#3").click(function(){
     $("#14").hide(); 
     $("#3").hide();
     $("#1").show();
     $("#2").show();
     $("#5").hide();
     $("#4").hide();
     });
     });
     
     $(document).ready(function(){
     $("#12").on("keyup", function() {
       var value = $(this).val();
       if(value === $("#10").val()) {
           $("#11").hide();
          $("#4").show();
       } else {
          $("#11").show();
       }
      });
      }); 

      <c:if test= "${id > 0}">
      alert("Your are register Succesfully"); 
      </c:if> 
      <c:if test= "${id == 0}">
      alert("Sorry wrong password and email...."); 
      </c:if>  
      <c:if test= "${id == -1}">
      alert("Sorry!...email is already used try another..."); 
      </c:if>  
</script>
</html>
