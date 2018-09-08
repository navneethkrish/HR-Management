function openEvent(event, operation) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
          tabcontent[i].style.display = "none";
             
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(operation).style.display = "block";
        event.currentTarget.className += " active";
}
setTimeout(function(){
           document.getElementById("default").click();
           }, 50);

     var jQueryScript = document.createElement('script');  
    jQueryScript.setAttribute('src','https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js');
    document.head.appendChild(jQueryScript)

    $(document).ready(function(){
        $(".view").click(function(){
            $(this).closest('tr').next('tr').toggle();
        });
    });

    $(document).ready(function(){
       $("#myInput").on("keyup", function() {
          var value = $(this).val().toLowerCase();
          $("#search tr").filter(function() {
              $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
          });
       });
    });   
    
     $(function() {  
            $( "#resizable" ).resizable();  
         });  
function myAlert(msg) {
   alert(msg); 
}
 

   


