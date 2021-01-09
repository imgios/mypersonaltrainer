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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer - Le tue schede</title>
    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    -->

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

</head>
<body>
<%  TrainingPlanService tp = new TrainingPlanServiceImpl();
    Collection<TrainingPlan> trainingPlanList = tp.getTrainingPlans("provatest@prova.io");
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

<%@include file="footer.jsp"%>
</body>
</html>