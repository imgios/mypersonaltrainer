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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>

        #t01 {
            text-align: center;
            margin-left:auto;
            margin-right:auto;
            table-layout: fixed;
            width: 800px;
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
        }

        #t01 td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #t01 tr:nth-child(even){background-color: #f2f2f2;}

        #t01 tr:hover {background-color: #ddd;}

        #t01 th {
            text-align: center;
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #1e90ff;
            color: white;
        }

        .button {
            font-size: 12px;
            background-color: #1e90ff;
            border-radius: 12px;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<%TrainingPlanService tp = new TrainingPlanServiceImpl();
Collection<TrainingPlan> t = tp.getTrainingPlans("provatest@prova.io");
int i = t.size();%>
<section>
<table id="t01">
    <tr>
        <th>#</th>
        <th>Data</th>
        <th>Scheda</th>
        <th>Download</th>
    </tr>
    <%for(TrainingPlan b : t){%>
    <tr>
        <td><%=i--%></td>
        <td><%=b.getDate()%></td>
        <td>
            <form action="ViewTrainingPlan.jsp" method="post">
                    <% request.getSession().setAttribute("exerc", b.getExercises()); %>
                <button class="button" type="submit">Visualizza</button>
            </form>
        </td>
        <td>
            <form action="download-training-plan" method="post">
                <input type="hidden" name="date" value=<%=b.getDate()%>>
                <% request.getSession().setAttribute("exerc", b.getExercises()); %>
                <button class="button" type="submit">Download PDF!</button>
            </form>
        </td>
    </tr>
<%}%>
</table>
</section>
</body>
</html>