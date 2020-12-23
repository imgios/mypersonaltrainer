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
    <title>Creazione Account</title>
</head>
<body>

<h1>myPersonalTrainer</h1>

<!-- servlet+form -->
<!-- inserimento di verifica regular expression prima di mandare i dati per controllo alla servlet -->
<form action="CreateAccountServlet" method="post">
    Nome: <input type="text" name="name"/><br>
    Cognome: <input type="text" name="surname"/>
    Telefono: <input type ="text" name="phone"/>
    eMail: <input type="text" name="email"/>
    password: <input type="text" name="password"/>
    <input type="submit" value="submit"> <input type ="reset">
</form>
</body>
</html>
