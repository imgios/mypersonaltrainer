
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


                <%
                     if(cliente == null && admin == null)
                     {
                %>
                 <h5 class="card-title">Errore!</h5>
                 <p class="card-text">Non hai effettuato il login all'interno del sito.</p>
                 <a href="login.jsp" class="btn btn-primary">Login</a>

                     <%
                     } else if (cliente != null){
                      %>

                     <h5 class="card-title">Errore!</h5>
                     <p class="card-text">Non puoi accedere a questa pagina, non sei un PT.</p>
                     <a href="login.jsp" class="btn btn-primary">Home</a>

                     <%
                        } else if (admin != null){
                     %>

                     <h5 class="card-title">Errore!</h5>
                     <p class="card-text">Non puoi accedere a questa pagina, non sei utente.</p>
                     <a href="login.jsp" class="btn btn-primary">Home</a>


                     <%
                     }
                     %>


         </div>
         </div>
     </div>
 </div>


 <%@include file="footer.jsp"%>

</body>
</html>
