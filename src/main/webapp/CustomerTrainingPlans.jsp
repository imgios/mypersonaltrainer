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
        section {
            max-width: 600px;
            margin-left:auto;
            margin-right:auto;
        }
        table, th, td {
            border: 1px solid #000000;
            border-collapse: collapse;
            table-layout: fixed;
        }
        th, td {
            padding: 15px;
            text-align: center;
            font-size: 12px;
            height: 100%;
        }
        #t01 {
            width: 100%;
            background-color: whitesmoke;
        }
    </style>

</head>
<body>
<%TrainingPlanService tp = new TrainingPlanServiceImpl();
Collection<TrainingPlan> t = tp.getTrainingPlans("provatest@prova.io");
//devo settare da lenght al contrario
int i = 0;%>
<section>
<table id="t01" action="/ViewTrainingPlan">
    <tr>
        <th>Numero Scheda</th>
        <th>Data</th>
        <th>Vai alla Scheda</th>
        <th>Download</th>
    </tr>
    <%for(TrainingPlan b : t){ %>
    <tr>
        <td><p><%=++i%></p></td>
        <td><%=b.getDate()%></td>
        <td><button type="submit" id="View" name="vtp" value="viewtp">Vai alla scheda...</button></td>
        <td>button</td>
    </tr>
<%}%>
</table>
</section>
</body>
</html>