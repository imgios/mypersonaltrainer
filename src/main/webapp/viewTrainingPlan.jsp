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

    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    -->

</head>

<body>

<%String s = request.getParameter("exercises");%>

        <div class="card bg-light mb-3" style="max-width: 18rem;">
            <div class="card-header">myPersonalTrainer</div>
            <div class="card-body">
                <h5 class="card-title">La tua scheda</h5>
                <p class="card-text"><%=s%></p>
            </div>
        </div>

<%@include file="footer.jsp"%>

</body>
</html>
