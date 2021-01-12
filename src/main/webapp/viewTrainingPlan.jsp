<%--
  Created by IntelliJ IDEA.
  User: em
  Date: 04/01/2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer | La mia scheda</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>

<body>

<main>

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
</html>
