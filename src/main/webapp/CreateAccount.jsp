<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 22/12/20
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- inserire verifica che l'utente sia PT
isPT = true allora procedi, altrimenti errore. -->
<!-- da aggiustare quando sarà presente il login -->
<%
    Account utente = new Account("Mario","Rossi","12312312","email@email.it","password",1);
    if (utente.getRole() == 1){
        System.out.println("sei un Personal Trainer, puoi accedere alla pagina.");
    } else
    {
        System.out.println("sei Cliente, non puoi accedere!");
        response.sendRedirect("index.jsp"); //riporta sulla pagina di errore o sulla home per esempio
        return;
    }
%>

<html>
<head>
    <!-- script bootstrap prima del caricamento -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


    <title>Creazione Account</title>
</head>

<body>

<!-- script bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<h1>myPersonalTrainer</h1>

<!-- servlet+form -->
<!-- inserimento di verifica regular expression prima di mandare i dati per controllo alla servlet -->
<!--
<form action="CreateAccountServlet" method="post">
    Nome: <input type="text" name="name"/><br>
    Cognome: <input type="text" name="surname"/>
    Telefono: <input type ="text" name="phone"/>
    eMail: <input type="text" name="email"/>
    password: <input type="text" name="password"/>
    <input type="submit" value="submit"> <input type ="reset">
</form>
-->

<!--inserimento form bootstrap -->
<p>registrazione un nuovo account</p>

<div class="container">
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
        <!-- non mi piace molto quella casella telefono da sola, credo di metterla vicino al cognome, ma non sono simmetriche poi aaaaa -->
        <!-- </div> -->
        <!--
            <div class="form-group">
            <div class="form-group col-md-6">
                    <label for="phone">Telefono</label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="inserire numero di telefono">
                </div>
            </div>
        -->
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


<!-- ____________________________________________________________________________________ -->

<!-- visualizza messaggio nella pagina di jsp-->
<!-- Div in basso per errori -->
<div>
    <% String error = (String) request.getSession().getAttribute("errorToShow");
        if ( error != null)
        {%>
    <div class="alert alert-danger" role="alert"  id="errorDiv">
        <p><%= error %> </p>
    </div>
    <%}%>

    <% String success = (String) request.getSession().getAttribute("successToShow");
        if ( success != null)
        {%>
    <div class="alert alert-success" role="alert"  id="errorDiv">
        <p><%= success %> </p>
    </div>
    <%}%>
</div>





<!-- inserimento del file js -->
<script type="text/javascript" src="./js/controlregistration.js"></script>

<!--  inserimento footer -->
<%@ include file="./footer/footer.jsp"%>

</body>
</html>