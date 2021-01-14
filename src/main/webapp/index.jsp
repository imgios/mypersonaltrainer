

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
