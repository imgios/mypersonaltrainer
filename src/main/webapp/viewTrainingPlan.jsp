
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if (emailCliente == null)
    {
        response.sendRedirect("error.jsp");
    } else {
%>
<head>
    <title>myPersonalTrainer | La mia scheda</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>

<body>

<main>

    <div class="welcomeMessage">
        <h5> &nbsp;Scheda di Allenamento</h5>
    </div>

<%String s = request.getParameter("exercises");%>

<div class="card text-center">
    <div class="card-header"></div>
    <div class="card-body">
        <h5 class="card-title">La tua scheda</h5>
        <p class="card-text"><%=s%></p>
    </div>
    <div class="card-footer text-muted">
        Just do it!
    </div>
</div>


</main>

<%@include file="footer.jsp"%>

</body>
<% }%>
</html>
