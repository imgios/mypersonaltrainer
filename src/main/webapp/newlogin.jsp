<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 07/01/21
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- script -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <link rel="stylesheet" href="css/login.css"/>
    <title>Login Account</title>
    <%@include file="navbar.jsp"%>
</head>
<body>

<div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
    <div class="card card0 border-0">
        <div class="row d-flex">
            <div class="col-lg-6">
                <div class="card1 pb-5">
                    <div class="row px-3 justify-content-center mt-4 mb-5 border-line"> <img src="./img/loginimage.jpg" class="image"> </div>
                </div>
            </div>
            <div class="col-lg-6">
                <form class="card2 card border-0 px-4 py-5">

                    <!-- controllo servlet inserimento -->
                    <form method="post" action="LoginServlet" onsubmit="return validation();">
                    <div class="row px-3"> <label class="mb-1">
                        <h6 class="mb-0 text-sm">Email</h6>
                     <input class="mb-4" type="text" id="idEmail" name="email" placeholder="Inserisci un indirizzo email"> </div>
                    <div class="row px-3"> <label class="mb-1">
                        <h6 class="mb-0 text-sm">Password</h6>
                        <input type="password" id="idPassword" name="password" placeholder="Inserisci password"> </div>
                    <div class="row mb-3 px-3"> <button type="submit" id="login_btn" class="btn btn-blue text-center">Login</button> </div>
                    </form>
                </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
