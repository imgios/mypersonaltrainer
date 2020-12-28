<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>myPersonalTrainer | myAccount</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles/reset.min.css"/>
    <link rel="stylesheet" href="styles/style.css"/>
    <link rel="stylesheet" href="styles/header-11.css"/>

</head>
<body>

<header class="site-header">
    <div class="wrapper site-header__wrapper">
        <nav class="nav">
            <button class="nav__toggle" aria-expanded="false" type="button" style=" box-sizing: border-box; align-self: flex-start; border: none; background: none;">
                <img src="images\sidebar.png">
            </button>

            <ul class="nav__wrapper">
                <li class="nav__item"><a href="#">Dashboard</a></li>
                <li class="nav__item"><a href="#">Statistiche</a></li>
                <li class="nav__item"><a href="#">Agenda</a></li>
                <li class="nav__item"><a href="#">Cronologia Schede</a></li>
            </ul>
        </nav>
        <a href="#" class="brand"><img src="images\nlogo.png"></a>
        <div align="center">
                <img src="images/user1.png">
            <div><a href="#"> Logout </a>  <a href="#" > Profilo Utente </a></div>
        </div>

    </div>
</header>
<div>
    <%@include file="footer.jsp"%>
</div>
<!-- Header End -->

<script src="js/header-11.js"></script>

</body>
</html>