<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Collection" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl" %>

<!doctype html>
<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>

<html lang="en">

<head>

    <title>myPersonalTrainer | Clienti</title>
    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>
<body>

<%

    RequiredTrainingPlanService rtp = new RequiredTrainingPlanServiceImpl();
    Collection<RequiredTrainingPlan> rtpList = rtp.getAllRequestes();

%>

<main>

<div class="card">
    <div class="card-body">
        <table class="table table-sm">
            <thead>
            <tr>
                <th class="table-primary" scope="col">Cliente</th>
                <th class="table-primary" scope="col"> </th>
                <th class="table-primary" scope="col"> </th>
            </tr>

            </thead>
            <tbody>
            <% for(RequiredTrainingPlan r : rtpList) {
            %>
            <tr>
                <td><%=r.getEmail()%></td>
                <td><div class="col text-right">
                    <form action="createTrainingPlan.jsp?email=<%=r.getEmail()%>" method="post">
                        <button type="submit" class="btn btn-sm btn-outline-primary">Crea Scheda</button>
                    </form>
                </div>
                  </td>
                <td><div class="col text-right">
                    <form action="viewCustomerByPersonalTrainer.jsp?email=<%=r.getEmail()%>" method="post">
                        <button type="submit" class="btn btn-sm btn-outline-primary">Informazioni</button>
                    </form>
                </div>
                </td>
            </tr>
            <%
            }%>
            </tbody>
        </table>
    </div>
</div>

</main>

<%@include file="footer.jsp"%>

</body>
<%
    }
%>
</html>