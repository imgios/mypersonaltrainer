<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if(emailCliente == null)
        response.sendRedirect("login.jsp");
%>

<html>
<head>
    <title>myPersonalTrainer | myAccount</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css"/>
    <!--link rel="stylesheet" href="styles/reset.min.css"/>
    <link rel="stylesheet" href="styles/style.css"/>
    <link rel="stylesheet" href="styles/header-11.css"/-->

</head>
<body>
<body>
<!--HEADER START-->
<div id= "header">
    <%@include file="header.jsp"%>
</div>
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

<script src="js/header-11.js"></script>

</body>
</html>
