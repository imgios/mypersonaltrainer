<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl" %>
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
    // response.sendRedirect("login.jsp");
    {
        response.sendRedirect("error.jsp");
    } else{
%>

<html>
<head>

    <%@include file="meta.jsp" %>
    <%@include file="head.jsp" %>
    <%@include file="navbar.jsp" %>


            <%
                //cambiare quando finito
                String utente_dashboard = "prova@io.it";

    ParametersDAO paramDao = new ParametersDAOImpl();
    ParametersService sparam = new ParametersServiceImpl(paramDao);
    //  sparam.getByMail(email);
    ArrayList<Parameters> list = sparam.getByMail(utente_dashboard);
    //  for (Parameters param: list){
    //    System.out.println(param);
%>
    <%
    String passaggio ="";
    String dati = "";

    for (Parameters param: list){
    passaggio = passaggio + param.getweight() + ",";
    // System.out.println(passaggio);
    dati = dati + "'" + param.getinsertionDate()+ "'" + ",";
    // System.out.println(dati);
    }
%>

    <link rel="stylesheet" href="css/viewProgress.css"/>


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
<main>

    <div class="welcomeMessage">
        Benvenuto, <%=utente_sess.getName()%>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Earnings (Monthly)</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Earnings (Annual)</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div class="progress-bar bg-info" role="progressbar"
                                             style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pending Requests Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Pending Requests</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
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
                            <h4 class="card-title">non toccare questa</h4>

                            <p class="card-description"> Add class <code>.table</code> </p>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Profile</th>
                                    <th>VatNo.</th>
                                    <th>Created</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Jacob</td>
                                    <td>53275531</td>
                                    <td>12 May 2017</td>
                                    <td>
                                        <label class="badge badge-danger">Pending</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Messsy</td>
                                    <td>53275532</td>
                                    <td>15 May 2017</td>
                                    <td>
                                        <label class="badge badge-warning">In progress</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>John</td>
                                    <td>53275533</td>
                                    <td>14 May 2017</td>
                                    <td>
                                        <label class="badge badge-info">Fixed</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Peter</td>
                                    <td>53275534</td>
                                    <td>16 May 2017</td>
                                    <td>
                                        <label class="badge badge-success">Completed</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Dave</td>
                                    <td>53275535</td>
                                    <td>20 May 2017</td>
                                    <td>
                                        <label class="badge badge-warning">In progress</label>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="col-lg-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <p> le tue statistiche di allenamento</p>
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







<!-- da guardare -->
<!--
<div class="container">
    <div class="row">
        <div class="col">
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Example textarea</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <div class="w-100"></div>
            <div class="col">
                <div class="row justify-content-md-center">
                    <div class="row align-items-end">
                        <form action="< %=request.getContextPath()%>/ManageRequiredTrainingPlanServlet" method="post"
                              onsubmit="return stopsubmit(this);">
                            < % String email = "trainerino@testing.com";
                                RequiredTrainingPlan requireTest;
                                RequiredTrainingPlanDAO requiredTrainingPlanDao = new RequiredTrainingPlanDAOImpl();
                                RequiredTrainingPlanService requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDao);
                                boolean checked = requiredTrainingPlanService.searchAccountByEmail(email);
                                System.out.println("la mail è:" + email);
                                System.out.println("Cheked è" + checked);
                                if (!checked) { %>
                            <button type="submit" class="btn btn-primary">Richiedi una Nuova Scheda</button>
                            < % System.out.println("la mail aricontrollata è:" + email);
                            } else {%>
                            < %
                                requireTest = requiredTrainingPlanService.getAccountByEmail(email);
                                System.out.println("la mail aricontrollata è:" + email);
                                if (requireTest.getRequired() == 1) {
                            %>
                            <button type="submit" class="btn btn-primary" disabled>Richiedi una Nuova Scheda</button>
                            < % } else {%>
                            <button type="submit" class="btn btn-primary">Richiedi una Nuova Scheda</button>
                            < % }
                            }%>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
-->







<%@ include file="footer.jsp" %>

</body>
<% }%>
</html>