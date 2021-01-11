<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>

<html>
<head>
    <title>myPT | Account</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>
<body>

<%
    AccountDAO a_dao = new AccountDAOImpl();
    AccountService a_serv = new AccountServiceImpl(a_dao);
    Account admin_sess = a_serv.getAccountByEmail(emailAdmin);
%>

<main>

<div class="welcomeMessage">
    <h5> &nbsp; Benvenuto, <b><i><%=admin_sess.getName()%></i></b> </h5>
</div>










</main>

    <%@include file="footer.jsp"%>
</body>

<%
    }
%>
</html>
