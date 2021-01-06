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

    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        body {
            font-family: "Lato", sans-serif;
        }

        .bg-primary {
            background-color: #9fc5f8!important;
        }

        .sidebar {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #007bff;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
        }

        .sidebar a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 25px;
            color: #f1f1f1;
            display: block;
            transition: 0.3s;
        }

        .sidebar a:hover {
            color: #818181;
        }

        .sidebar .closebtn {
            position: absolute;
            top: 0;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        .openbtn {
            font-size: 20px;
            cursor: pointer;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
        }

        .openbtn:hover {
            background-color: #9fc5f8;
        }

        #main {
            transition: margin-left .5s;
            padding: 16px;
        }

        .navbar-nav a {
            color: white;
        }

        /*
        #logo {
            width: auto;
            height: auto;
        }
        */

        .logo {
            width: 80px;
            height: auto;
        }

        /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
        @media screen and (max-height: 450px) {
            .sidebar {padding-top: 15px;}
            .sidebar a {font-size: 18px;}
        }
    </style>

</head>
<body>


<%int role=0;%>


<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <% if (role == 0) { %>
    <div class="navbar-nav mr-auto">
        <button class="openbtn" onclick="openNav()">☰</button>
    </div>
    <% } if (role == 1) { %>
    <button class="openbtn" onclick="openNavPT()">☰</button>
    <% } %>


    <div class="navbar-nav mx-auto">
        <img class="logo" src="./img/nlogo.png">
    </div>



    <ul class="navbar-nav ml-auto">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
<!--
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>



        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    -->
</nav>
<!-- controllo blando per vedere se le navbar funzionano -->
<% if (role == 0) { %>
<!-- navbar cliente -->
<div id="mySidebarCliente" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
    <a href="#">Dashboard</a>
    <a href="#">Statistiche</a>
    <a href="#">Agenda</a>
    <a href="#">Cronologia Schede</a>
</div>
<%
    }
%>

 <% if (role == 1) { %>
 <!-- navbar pt  -->
<div id="mySidebarPT" class="sidebar">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNavPT()">×</a>
    <a href="#">Dashboard</a>
    <a href="#">Agenda</a>
    <a href="#">Clienti</a>
    <a href="#">Schede</a>
    <a href="#">Pagamenti</a>
</div>

<%
}
%>

<div id="main">
    <h1>Prova</h1>
</div>



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
