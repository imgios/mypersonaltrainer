<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="java.util.Collection" %>

<!doctype html>

<html lang="en">

<head>

    <title>myPersonalTrainer | Clienti</title>
    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>
<body>

<%
    AccountService accounts = new AccountServiceImpl();
    Collection<Account> accountsBean = accounts.viewInfoAccount();
    int i = 0;
%>

<div class="card">
    <div class="card-body">
<table class="table table-sm">
    <thead>
    <tr>
        <th class="table-primary" scope="col">#</th>
        <th class="table-primary" scope="col">Nome</th>
        <th class="table-primary" scope="col">Cognome</th>
        <th class="table-primary" scope="col"> </th>
    </tr>

    </thead>
    <tbody>
    <% for(Account a : accountsBean) {
        if(a.getRole() != 1){
    %>
    <tr>
        <th scope="row"><%=i++%></th>
        <td><%=a.getName()%></td>
        <td><%=a.getSurname()%></td>
        <td><div class="col text-right">
        <form action="viewCustomerByPersonalTrainer.jsp?email=<%=a.getEmail()%>" method="post">
            <button type="submit" class="btn btn-sm btn-outline-primary">Informazioni</button>
        </form>
        </div>
        </td>
    </tr>
    <%}
    }%>
        </tbody>
        </table>
    </div>
</div>

<%@include file="footer.jsp"%>

</body>
</html>