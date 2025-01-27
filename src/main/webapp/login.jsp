
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<!-- inserimento controllo della sessione, se loggato non visualizza il login ma la propria pagina cliente -->
<%
    String utente = (String) request.getSession().getAttribute("clienteMail");
    String pt = (String) request.getSession().getAttribute("ptMail");
%>
<%
    if (utente != null) {
    response.sendRedirect("./customerDashboard.jsp");
    } else if (pt != null) {
    response.sendRedirect("./adminDashboard.jsp");
    }
%>
<head>

    <%@include file="meta.jsp" %>
    <%@include file="head.jsp" %>
    <%@include file="navbar.jsp" %>

    <link rel="stylesheet" href="css/login.css"/>

    <title>Login Account</title>
</head>
<body>
<main>
    <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
        <div class="card card0 border-0">
            <div class="row d-flex">
                <div class="col-lg-6">
                    <div class="card1 pb-5">
                        <div class="row px-3 justify-content-center mt-4 mb-5 border-line"><img
                                src="./img/login_image.jpg" class="image"></div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <!--
                    <form class="card2 card border-0 px-4 py-5">
                    -->
                    <!-- controllo servlet inserimento -->
                    <form class="card2 card border-0 px-4 py-5" action="<%=request.getContextPath()%>/LoginServlet"
                          method="post" onsubmit="return stopsubmit(this);">
                        <div class="row px-3"> <label class="mb-1" for="idEmail"> </label>
                            <!--<label for="idEmail">--> <h6 class="mb-0 text-sm">Email &nbsp;</h6> <label
                                id="controlEmail"></label> <!--</label>-->
                            <input class="mb-4" type="email" id="idEmail" name="email" onkeyup="validateEmail()"
                                   placeholder="Inserisci un indirizzo email"> <!-- </label> --> </div>
                        <div class="row px-3"> <label class="mb-1" for="idPassword"> </label>
                          <!--  <label for="idPassword"> --><h6 class="mb-0 text-sm">Password &nbsp;</h6> <label
                                    id="controlPassword"></label>
                            <input type="password" id="idPassword" name="password" onkeyup="validatePassword()"
                                   placeholder="Inserisci password"> <!--</label> --> </div>
                        <div class="row mb-3 px-3">
                            <button type="submit" id="login_btn" class="btn btn-blue text-center">Login</button>
                        </div>
                    </form>
                    <!--
                     </form> -->

                    <!-- inserimento messaggi di errore -->
                    <div>
                        <% String error = (String) request.getSession().getAttribute("errorInsertLogin");
                            if (error != null) {%>
                        <div class="alert alert-danger" role="alert" id="errorDiv">
                            <p><%= error %>
                            </p>
                        </div>
                        <%}%>

                        <% String success = (String) request.getSession().getAttribute("successInsertLogin");
                            if (success != null) {%>
                        <div class="alert alert-success" role="alert" id="errorDiv">
                            <p><%= success %>
                            </p>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>


</main>

<script type="text/javascript" src="js/loginControl.js"></script>

<%@include file="footer.jsp" %>
</body>
</html>