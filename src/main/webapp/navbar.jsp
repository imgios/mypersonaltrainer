<%--
  Created by IntelliJ IDEA.
  User: Michele
  Date: 05/01/2021
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer</title>

    <!-- Bootstrap
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      -->

    <!-- logo icons
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/v4-shims.css">
    -->

    <!-- inserimento import head and meta data -->
    <%@include file="./meta.jsp"%>
    <%@include file="./head.jsp"%>

    <link rel="stylesheet" href="css/navbar.css"/>

</head>
<body>

<%
    String clienteMail = (String) request.getSession().getAttribute("clienteMail");
    String adminMail = (String) request.getSession().getAttribute("ptMail");

    if(clienteMail == null && adminMail == null)
    {
%>
                        <!-- Homepage nessuno loggato -->
<nav class="navbar navbar-expand-lg navbar-custom_navbar">
<!--
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
-->
    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>
</nav>
<% } else if (clienteMail != null) {%>


                        <!-- NAVBAR CLIENTE -->
<!--
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
-->
<nav class="navbar navbar-expand-lg navbar-custom_navbar">
    <div class="navbar-nav mr-auto">
        <button class="openbtn" onclick="openNav()">☰</button>
    </div>

    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>

    <ul class="navbar-nav ml-auto">
        <li><a href="./AccountProfile.jsp"><i class="fas fa-user"></i>Profilo &nbsp;</a></li>
        <li><a href="<%=request.getContextPath()%>/LogoutServlet"><i class="fas fa-sign-out-alt"></i>Logout</a></li>
    </ul>
</nav>

<div id="mySidebarCliente" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
    <a href="#">Dashboard</a>
    <a href="#">Statistiche</a>
    <a href="#">Agenda</a>
    <a href="#">Cronologia Schede</a>
</div>

<% } else if (adminMail != null) {%>

                            <!-- NAVBAR PT -->
<!--
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
-->
<nav class="navbar navbar-expand-lg navbar-custom_navbar">
    <div class="navbar-nav mr-auto">
        <button class="openbtn" onclick="openNavPT()">☰</button>
    </div>

    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>

    <ul class="navbar-nav ml-auto">
        <!--<li><a href="./PTProfile.jsp"><i class="fas fa-user"></i> Profilo &nbsp;</a></li> -->
        <li><a href="<%=request.getContextPath()%>/LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
    </ul>
</nav>

<div id="mySidebarPT" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNavPT()">×</a>
    <a href="#">Dashboard</a>
    <a href="#">Agenda</a>
    <a href="#">Clienti</a>
    <a href="#">Schede</a>
    <a href="#">Pagamenti</a>
</div>

<% }%>




<!-- script controllo sidebar -->
<script>
    function openNav() {
        document.getElementById("mySidebarCliente").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidebarCliente").style.width = "0";
        document.getElementById("main").style.marginLeft= "0";
    }
</script>

<script>
  function openNavPT() {
    document.getElementById("mySidebarPT").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
  }

  function closeNavPT() {
    document.getElementById("mySidebarPT").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
  }
</script>

<!-- script bootstrap
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
-->

</body>
</html>
