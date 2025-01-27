<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page language="java" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if (emailCliente == null)
    {
        response.sendRedirect("error.jsp");
    } else {
%>

<html>
<head>

    <%@include file="meta.jsp" %>
    <%@include file="head.jsp" %>
    <%@include file="navbar.jsp" %>


    <%
        ParametersDAO paramDao = new ParametersDAOImpl();
        ParametersService sparam = new ParametersServiceImpl(paramDao);

        ArrayList<Parameters> list = sparam.getByMail(emailCliente);

    %>

    <%
        String passaggio ="";
        String dati = "";

        for (Parameters param: list){
            passaggio = passaggio + param.getweight() + ",";

            dati = dati + "'" + param.getinsertionDate()+ "'" + ",";

        }
    %>

    <link rel="stylesheet" href="css/viewProgress.css"/>

    <link rel="stylesheet" href="css/dashboard.css"/>

    <title>myPersonalTrainer | myAccount</title>

</head>


<%
    SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
    SubscriptionService subService = new SubscriptionServiceImpl(subscriptionDAO);
    int state = subService.checkSubscriptionState(emailCliente);
    if (state == 0) {
        subService.checkIfSent(emailCliente);
    }
%>

<%
    AccountDAO adao = new AccountDAOImpl();
    AccountService aserv = new AccountServiceImpl(adao);
    Account utente_sess = aserv.getAccountByEmail(emailCliente);
%>

<%
    String s = request.getParameter("exercises");
%>

<main>

    <div class="welcomeMessage">
        <h1> &nbsp;Benvenuto, <b><i><%=utente_sess.getName()%></i></b> </h1>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- Prenota Appuntamento -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                <a href="./requestAppointment.jsp" style="color: #4e73df">Prenota Appuntamento</a></div>
                        </div>
                        <div class="col-auto">
                            <i class="far fa-calendar-alt fa-2x"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Statistiche -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                <a href="./viewProgress.jsp" style="color: #1cc88a">Statistiche</a></div>

                        </div>
                        <div class="col-auto">
                            <i class="fas fa-chart-line fa-2x"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
        RequiredTrainingPlan requireTest;
        RequiredTrainingPlanDAO requiredTrainingPlanDao = new RequiredTrainingPlanDAOImpl();
        RequiredTrainingPlanService requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDao);
        boolean checked = requiredTrainingPlanService.searchAccountByEmail(utente_sess.getEmail());
        if (!checked) { %>

        <!-- Richiedi nuova scheda abled -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1"><a href="./requestTrainingPlan.jsp" style="color: #36b9cc">Richiedi scheda</a>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%} else {%>
        <%
        requireTest = requiredTrainingPlanService.getAccountByEmail(utente_sess.getEmail());
        if (requireTest.getRequired() == 1) {
        %>
        <!-- Richiedi nuova scheda disabled -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Scheda gi� richiesta<br>Attendere la creazione
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% } else {%>

        <!-- Richiedi nuova scheda abled -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1"><a href="./requestTrainingPlan.jsp"  style="color: #36b9cc">Richiedi scheda</a>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% }
        }%>

        <!-- Abbonamento -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                <a href="./accountProfile.jsp" style="color: #ffc107">Abbonamento</a></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-bell fa-2x"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="main-panel">
        <div class="content-wrapper">
            <div class="row">
                <div class="col-lg-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Scheda Allenamento</h4>
                            <!--
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Esercizio</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>< %=s%></td>
                                </tr>

                                </tbody>
                            </table>
                                -->
                            <a class="btn btn-primary" href="./customerTrainingPlans.jsp" role="button">Schede di Allenamento precedenti</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Status Allenamento</h4>
                            <!-- inserimento grafici -->

                            <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

                            <div class="chart-container" style="position: relative; height:auto; width:auto">
                                <canvas id="myChart"></canvas>
                                <script>
                                  var ctx = document.getElementById('myChart').getContext('2d');
                                  var chart = new Chart(ctx, {
                                    // The type of chart we want to create
                                    type: 'line',

                                    // The data for our dataset
                                    data: {
                                      labels: [<%=dati%>],
                                      datasets: [{
                                        label: 'Peso',
                                        backgroundColor: 'rgb(74, 104, 254)',
                                        borderColor: 'rgb(9,217,245)',
                                        data: [<%=passaggio%>]

                                      }]
                                    },

                                    // Configuration options go here
                                    /*
                                    options: {
                                      layout: {
                                        padding: {
                                          left: 300,
                                          right: 0,
                                          top: 0,
                                          bottom: 0
                                        }
                                      }
                                    }
                                     */
                                  });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</main>

<%@ include file="footer.jsp" %>
</body>
<% }%>
</html>