<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="java.util.Collection" %>

<!doctype html>
<html lang="en">

<head>

    <title>Clienti</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>


</head>

<body style="background-color:powderblue;">

<div width: 350px; height: 200px; position: absolute;  top: 50%; left: 50%; margin: -100px 0 0 -175px;>
    <ul class="list-group">

        <%AccountService accounts = new AccountServiceImpl();
        Collection<Account> p = accounts.viewInfoAccount(); %>

        <%for(Account a : p) {%>
        <li class="list-group-item d-flex justify-content-between align-items-center">
                <%=a.getName()+" "+a.getSurname()+", "+a.getEmail()+", "+a.getPhone()%>
            <span class="badge bg-primary rounded-pill">!</span><% }%>
    </ul>
</div>



<%@include file="footer.jsp"%>

</body>
</html>
