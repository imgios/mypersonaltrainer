<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 28/12/20
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String utente_reg = (String) request.getSession().getAttribute("clienteMail");
    String pt_user = (String) request.getSession().getAttribute("ptMail");
%>
<% if (utente_reg != null) {
    response.sendRedirect("./customerDashboard.jsp");
} else if (pt_user != null) {
    response.sendRedirect("./adminDashboard.jsp");
} else if (utente_reg == null && pt_user == null){
    response.sendRedirect("./login.jsp");
}
%>

<html>
<head>
    <title>myPersonalTrainer</title>

</head>
<body>

</body>
</html>
