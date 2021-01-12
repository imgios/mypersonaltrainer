<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl" %>
<%@ page import="java.util.Collection" %>
<%--
  Created by IntelliJ IDEA.
  User: em
  Date: 31/12/2020
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>

<%
    String utente_email_sess = (String) request.getSession().getAttribute("clienteMail");

    if (utente_email_sess == null) {
        response.sendRedirect("./error.jsp");
    }else {
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer - Le tue schede</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>
<body>

<main>

<%
    TrainingPlanService tp = new TrainingPlanServiceImpl();
    Collection<TrainingPlan> trainingPlanList = tp.getTrainingPlans(utente_email_sess);
    int i = trainingPlanList.size();
%>

<div class="card">
    <div class="card-body">
        <table class="table table-sm">
            <thead>
            <tr>
                <th class="table-primary" scope="col">#</th>
                <th class="table-primary" scope="col">Data</th>
                <th class="table-primary" scope="col"> </th>
                <th class="table-primary" scope="col"> </th>
            </tr>
            </thead>
            <tbody>
            <% for(TrainingPlan t : trainingPlanList) {
            %>
            <tr>
                <th scope="row"><%=i--%></th>
                <td><%=t.getDate()%></td>
                <td>
                    <div class="col text-right">
                        <form action="viewTrainingPlan.jsp?exercises=<%=t.getExercises()%>" method="post">
                            <button type="submit" class="btn btn-sm btn-outline-primary">Visualizza</button>
                        </form>
                    </div>
                </td>
                <td>
                    <div class="col text-right">
                        <form action="download-training-plan" method="post">
                            <input type="hidden" name="date" value=<%=t.getDate()%>>
                            <input type="hidden" name="exercises" value="<%=t.getExercises()%>">
                            <button class="btn btn-sm btn-outline-primary" type="submit">Download PDF!</button>
                        </form>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

</main>

<%@include file="footer.jsp"%>
</body>
</html>

<%
    }
%>