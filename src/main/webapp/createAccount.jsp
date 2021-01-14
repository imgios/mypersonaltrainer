<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String personaltrainer = (String) request.getSession().getAttribute("ptMail");
    if (personaltrainer == null){
      response.sendRedirect("./error.jsp");
    }
%>

<html>
<head>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>


    <!-- inserimento del file js -->
    <script type="text/javascript" src="js/controlRegistration.js"></script>


    <title>Creazione Account</title>
</head>
<body>

<main>

<div class="container">

    <h4>Registrazione di un nuovo Account</h4>

    <form action="<%=request.getContextPath()%>/CreateAccountServlet" method="post" onsubmit="return stopsubmit(this);">

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="name">Nome</label> <label id="controlName"></label>
                <input type="text" class="form-control" name="name" id="name" onkeyup="validateName()" placeholder="inserire nome">
            </div>
            <div class="form-group col-md-6">
                <label for="surname">Cognome</label> <label id="controlSurname"></label>
                <input type="text" class="form-control" name="surname" id="surname" onkeyup="validateSurname()" placeholder="inserire cognome">
            </div>
            <div class="form-group col-md-6">
                <label for="phone">Telefono</label> <label id="controlPhone"></label>
                <input type="text" class="form-control" id="phone" name="phone" onkeyup="validatePhone()" placeholder="inserire numero di telefono">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="email">Email</label> <label id="controlEmail"></label>
                <input type="email" class="form-control" name="email" id="email" onkeyup="validateEmail()" placeholder="Email">
            </div>
            <div class="form-group col-md-6">
                <label for="password">Password</label> <label id="controlPassword"></label>
                <input type="password" class="form-control" name="password" id="password" onkeyup="validatePassword()" placeholder="Password">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Registrati</button>
    </form>
</div>


<!-- Div in basso per errori -->
<div>
    <% String error = (String) request.getSession().getAttribute("errorMessage");
        if ( error != null)
        {%>
    <div class="alert alert-danger" role="alert"  id="errorDiv">
        <p><%= error %> </p>
    </div>
    <%}%>

    <% String success = (String) request.getSession().getAttribute("successMessage");
        if ( success != null)
        {%>
    <div class="alert alert-success" role="alert"  id="errorDiv">
        <p><%= success %> </p>
    </div>
    <%}%>
</div>



</main>

<!--  inserimento footer -->
<%@ include file="footer.jsp"%>

</body>
</html>