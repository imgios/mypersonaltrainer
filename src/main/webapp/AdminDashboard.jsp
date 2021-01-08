<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- verifica che l'utente sia PT
isPT = true allora procedi, altrimenti errore.-->
<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null)
        //response.sendRedirect("Login.jsp");
          response.sendRedirect("error.jsp");
%>

<html>
<head>
    <title>myPersonalTrainer | myAccount</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

   <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    -->

    <!--inserimento navbar
    < %@ include file="navbar.jsp"%>
    -->
</head>
<body>
    <div class="wrapper">
        <div class="main_container">
            <div class="item">
                <div class="welcomeMessage">
                    Benvenuto, Admin
                </div>
            </div>
            <div class="item">

            </div>
        </div>
    </div>


    <!--FOOTER -->
    <%@include file="footer.jsp"%>
    <!--FINE FOOTER-->

</body>
</html>