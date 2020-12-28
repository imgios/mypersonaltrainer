<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 28/12/20
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>

<!-- check if the user is log--
<%
    String client_email = (String) request.getSession().getAttribute("email");
    System.out.println(client_email);
    if (client_email == null){
        response.sendRedirect("index.jsp");   //login page che non ho al momento
    }
%>
<%  //check of the parameters
 //devo prendere i parametri dalla sessione?
%>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Progressi Cliente</title>
</head>
<body>
<h1>
</body>
</html>
