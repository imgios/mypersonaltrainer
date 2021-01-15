<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl" %>
<%@ page import="java.util.Collection" %>


<%
    String utente_email_sess = (String) request.getSession().getAttribute("clienteMail");
    if ( utente_email_sess == null ){
        response.sendRedirect("./error.jsp");
    } else {
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer - Le tue schede</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <link rel="stylesheet" type="text/css" href="./css/viewTrainingPlan.css">

</head>
<body>

<main>

    <div class="welcomeMessage">
        <h5> &nbsp;Le tue schede</h5>
    </div>
    <!-- <p class="font-weight-bold">Le tue schede</p> -->

<%
    TrainingPlanService tp = new TrainingPlanServiceImpl();
    Collection<TrainingPlan> trainingPlanList = tp.getTrainingPlans(utente_email_sess);
    int i = 0;
%>

    <%
        for(TrainingPlan t : trainingPlanList) {
    %>

    <section id="scheda">
        <details>
            <summary>
                <%=t.getDate()%>
            </summary>
            <p>
                <%String ex = t.getExercises().replaceAll("nome esercizio:", "<br>");%>
                <%=ex%>
            <div class="col text-right">
            <form action="<%=request.getContextPath()%>/DownloadSchedaServlet" method="post">
                <input type="hidden" name="date" value=<%=t.getDate()%>>
                <input type="hidden" name="exercises" value="<%=t.getExercises()%>">
                <button class="btn btn-sm btn-outline-primary" type="submit">Download PDF!</button>
            </form>
            </div>
            </p>
        </details>
    </section>

    <%
        }
    %>


</main>

<%@include file="footer.jsp"%>

<%
    }
%>

</body>
</html>
