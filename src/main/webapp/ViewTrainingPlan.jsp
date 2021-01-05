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
    <title>Le Mie Schede</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/ViewTrainingPlan.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
    TrainingPlanService tp = new TrainingPlanServiceImpl();
    Collection<TrainingPlan> t = tp.getTrainingPlans("clientemail@prova.io");
%>
<section>
    <h2>Le mie schede</h2>
    <%for (TrainingPlan b : t) { %>
    <details>
        <summary>
            <form action="download-training-plan" method="post">
                <%=b.getDate()%>
                <input type="hidden" name="date" value=<%=b.getDate()%>>

                <% request.getSession().setAttribute("exerc", b.getExercises()); %>

                <button type="submit">Download PDF!</button>
            </form>
        </summary>
        <p>
            <%= b.getExercises()%>
        </p>
    </details>
    <%}%>

</section>
</ul>
</body>
</html>