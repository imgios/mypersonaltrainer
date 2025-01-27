<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl" %>
<%@ page import="java.util.Collection" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanServiceImpl" %>


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

%>

    <%
        if (trainingPlanList.size() == 0){
          %>
    <%
        RequiredTrainingPlan requireTest;
        RequiredTrainingPlanDAO requiredTrainingPlanDao = new RequiredTrainingPlanDAOImpl();
        RequiredTrainingPlanService requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDao);
        boolean checked = requiredTrainingPlanService.searchAccountByEmail(utente_email_sess);
        if (!checked) { %>

    <!-- Richiedi nuova scheda abled -->
    <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
        <div class="card card0 border-0">

            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">ALT!</h5>
                    <p class="card-text">Non hai richiesto schede di allenamento!</p>
                    <a href="requestTrainingPlan.jsp" class="btn btn-primary">Richiedi Scheda</a>
                </div>
            </div>
        </div>
    </div>
    <%} else {%>
    <%
        requireTest = requiredTrainingPlanService.getAccountByEmail(utente_email_sess);
        if (requireTest.getRequired() == 1) {
    %>
    <!-- Richiedi nuova scheda disabled -->
    <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
        <div class="card card0 border-0">

            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">ALT!</h5>
                    <p class="card-text">Non hai richiesto schede di allenamento!</p>
                    <input type="button" class="btn btn-primary" disabled value="Richiedi Scheda">
                </div>
            </div>
        </div>
    </div>
    <% } else {%>

    <!-- Richiedi nuova scheda abled -->
    <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
        <div class="card card0 border-0">

            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">ALT!</h5>
                    <p class="card-text">Non hai richiesto schede di allenamento!</p>
                    <a href="requestTrainingPlan.jsp" class="btn btn-primary">Richiedi Scheda</a>
                </div>
            </div>
        </div>
    </div>
    <% }
    }%>
    <% } else { %>
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

<%
    }
%>
</body>
</html>
