
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer</title>


    <!-- inserimento import head and meta data -->
    <%@include file="./meta.jsp"%>
    <!--
    < %@include file="./head.jsp"%>
    -->
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

    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>
</nav>
<% } else if (clienteMail != null) {%>


                        <!-- NAVBAR CLIENTE -->

<nav class="navbar navbar-expand-lg navbar-custom_navbar">
    <div class="navbar-nav mr-auto">
        <button class="openbtn" onclick="openNav()">☰</button>
    </div>

    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>

    <ul class="navbar-nav ml-auto">
        <li><a href="accountProfile.jsp"><i class="fas fa-user"></i>Profilo &nbsp;</a></li>
        <li><a href="<%=request.getContextPath()%>/LogoutServlet"><i class="fas fa-sign-out-alt"></i>Logout</a></li>
    </ul>
</nav>

<div id="mySidebarCliente" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
    <a href="customerDashboard.jsp">Dashboard</a>
    <a href="viewProgress.jsp">Statistiche</a>
    <a href="requestAppointment.jsp">Agenda</a>
    <a href="customerTrainingPlans.jsp">Cronologia Schede</a>
</div>

<% } else if (adminMail != null) {%>

                            <!-- NAVBAR PT -->

<nav class="navbar navbar-expand-lg navbar-custom_navbar">
    <div class="navbar-nav mr-auto">
        <button class="openbtn" onclick="openNavPT()">☰</button>
    </div>

    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>

    <ul class="navbar-nav ml-auto">

        <li><a href="<%=request.getContextPath()%>/LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
    </ul>
</nav>

<div id="mySidebarPT" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNavPT()">×</a>
    <a href="adminDashboard.jsp">Dashboard</a>
    <a href="verifyAppointments.jsp">Agenda</a>
    <a href="customersList.jsp">Clienti</a>
    <a href="viewRequiredTrainingPlans.jsp">Schede</a>
    <a href="viewSubscriptionList.jsp">Abbonamenti</a>
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



</body>
</html>
