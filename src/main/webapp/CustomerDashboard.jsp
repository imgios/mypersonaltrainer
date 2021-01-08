<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if(emailCliente == null)
       // response.sendRedirect("Login.jsp");
          response.sendRedirect("error.jsp");
%>

<html>
<head>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <title>myPersonalTrainer | myAccount</title>
    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="css/footer.css"/>
      -->
    <!--link rel="stylesheet" href="styles/reset.min.css"/>
    <link rel="stylesheet" href="styles/footer.css"/>
    <link rel="stylesheet" href="styles/header-11.css"/-->
    <!-- inserimento navbar
    < % @ include file="navbar.jsp"%>
     -->

</head>
<body>

    <div class="wrapper">
        <div class="main_container">
            <div class="item">
                <div class="welcomeMessage">
                    Benvenuto, Cliente
                </div>
            </div>

            <div class="item">

            </div>
        </div>
    </div>

<!--HEADER END-->



    <%@ include file="footer.jsp"%>

</body>
</html>
