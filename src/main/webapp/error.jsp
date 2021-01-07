<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 07/01/21
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<!-- check che tipo di errore devo visualizzare -->
<%
    String cliente = (String) request.getSession().getAttribute("clienteMail");
    String admin = (String) request.getSession().getAttribute("ptMail");
%>

<head>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <link rel="stylesheet" href="css/error.css"/>

    <title>Error!</title>
</head>
<body>

 <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
     <div class="card card0 border-0">

         <div class="card text-center">
             <div class="card-body">

     <!--    <div class="row d-flex">
             <div class="col-lg-6">  -->
              <!--   <div class="card1 pb-5"> -->
                <%
                     if(cliente == null && admin == null)
                     {
                %>
                 <h5 class="card-title">Errore!</h5>
                 <p class="card-text">Non hai effettuato il login all'interno del sito.</p>
                 <a href="#" class="btn btn-primary">Go somewhere</a>

                <!--    <div class="row px-3 justify-content-center mt-4 mb-5 border-line"> <h1> ERRORE</h1> </div>
                     <div class="row px-3 justify-content-center mt-4 mb-5 border-line"> <h1> NON SEI REGISTRATO</h1> </div>
                 </div> -->

                     <%
                     } else if (cliente != null){
                  %>

             <h5 class="card-title">Errore!</h5>
             <p class="card-text">Non puoi accedere a questa pagina, non sei un PT.</p>
             <a href="#" class="btn btn-primary">Go somewhere</a>


                 <!--    <div class="row px-3 justify-content-center mt-4 mb-5 border-line"> <h1> NON SEI UN PT, non puoi accedere</h1> </div> -->
                    <%
                        } else if (admin != null){
                    %>

             <h5 class="card-title">Errore!</h5>
             <p class="card-text">Non puoi accedere a questa pagina, non sei utente.</p>
             <a href="#" class="btn btn-primary">Go somewhere</a>


                  <!--   <div class="row px-3 justify-content-center mt-4 mb-5 border-line"> <h1> NON SEI UN UTENTE NON PUOI ACCEDERE</h1> </div> -->
                     <%
                     }
                     %>
        <!--         </div>
             </div> -->

         </div>
         </div>

         <!--
       <div class="card text-center">
        <div class="card-header">
               <ul class="nav nav-pills card-header-pills">
                   <li class="nav-item">
                       <a class="nav-link active" href="#">Active</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href="#">Link</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link disabled" href="#">Disabled</a>
                   </li>
               </ul>
           </div>
             <div class="card-body">
                 <h5 class="card-title">Special title treatment</h5>
                 <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                 <a href="#" class="btn btn-primary">Go somewhere</a>
             </div>
         </div>

            -->



     </div>










 </div>
 </div>





 <%@include file="footer.jsp"%>

</body>
</html>
