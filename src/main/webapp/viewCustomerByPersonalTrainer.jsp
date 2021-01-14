<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.TrainingPlanServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan" %>
<%@ page import="java.util.List" %>

<!--check if the user is log-->
<%
    String email = (String) request.getParameter("email");
    System.out.println(email);
    if (email == null){
        response.sendRedirect("index.jsp");   //login page che non ho al momento
    }
%>
<%  //check parameters %>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPersonalTrainer | Informazioni Cliente</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>


    <!--
    <link href="css/footer.css" rel="stylesheet" type="text/css">
    -->

</head>
<body>

<main>

<%
    ParametersDAO paramDao = new ParametersDAOImpl();
    ParametersService sparam = new ParametersServiceImpl(paramDao);
    Account accountCustomer;

    AccountDAO accountDao=new AccountDAOImpl();
    AccountService serviceAccount = new AccountServiceImpl(accountDao);

    TrainingPlanDAO trainingDAO=new TrainingPlanDAOImpl();
    TrainingPlanService serviceTrainingPlan = new TrainingPlanServiceImpl();
    List<TrainingPlan> trainingList = serviceTrainingPlan.getTrainingPlans(email); //list of Training Plan

    ArrayList<Parameters> list = sparam.getByMail(email);
    accountCustomer=serviceAccount.getAccountByEmail(email);
%>

<%
    String passaggio ="";
    String dati = "";
    String massamagra = "";
    String massagrassa = "";
    for (Parameters param: list){
        passaggio = passaggio + param.getweight() + ",";
        dati = dati + "'" + param.getinsertionDate()+ "'" + ",";
        massamagra = massamagra + param.getfatMass() + ",";
        massagrassa = massagrassa + param.getleanMass() + ",";
    }
%>
<!--inserimento script-->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<div id = "main">

    <div class="card">
        <h5 class="card-header"> <i class="fas fa-user"></i> <%=accountCustomer.getName()%> <%=accountCustomer.getSurname()%> <br></h5>
        <h7 class="card-header"> Informazioni</h7>
        <div class="card-body">
            <form>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Name</label>
                        <input type="email" class="form-control" value="<%=accountCustomer.getName()%>" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Surname</label>
                        <input type="email" class="form-control" value="<%=accountCustomer.getSurname()%>" disabled>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Email</label>
                        <input type="email" class="form-control" value="<%=accountCustomer.getEmail()%>" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Phone</label>
                        <input type="email" class="form-control" value="<%=accountCustomer.getPhone()%>" disabled>
                    </div>
                </div>
            </form>
        </div>
    </div>

        <!--First Chart leanMass-->
        <div class="chart-container" style="position: relative; height:auto; width:80vw">
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
                    options: {
                        layout: {
                            padding: {
                                left: 90,/*300*/
                                right: 0,
                                top: 0,
                                bottom: 0
                            }
                        }
                    }
                });
            </script>
        </div>

        <!--Second Chart fatMass -->
        <div>
            <div class="chart-container" style="position: relative; height:auto; width:80vw">
                <canvas id="myChart2"></canvas>
                <script>
                    var ctxx = document.getElementById('myChart2').getContext('2d');
                    var chart = new Chart(ctxx, {
                        // The type of chart we want to create
                        type: 'line',
                        // The data for our dataset
                        data: {
                            labels: [<%=dati%>],
                            datasets: [{
                                label: 'Massa Magra',
                                backgroundColor: 'rgb(30,196,255)',
                                borderColor: 'rgb(30,252,209)',
                                data: [<%=massamagra%>]
                            }]
                        },
                        // Configuration options go here
                        options: {
                            layout: {
                                padding: {
                                    left: 90,/*300*/
                                    right: 0,
                                    top: 0,
                                    bottom: 0
                                }
                            }
                        }
                    });
                </script>
            </div>
        </div>


        <div>
            <!--Third Chart Weight-->
            <div class="chart-container" style="position: relative; height:auto; width:80vw">
                <canvas id="myChart3"></canvas>
                <script>
                    var ctxxx = document.getElementById('myChart3').getContext('2d');
                    var chart = new Chart(ctxxx, {
                        // The type of chart we want to create
                        type: 'line',
                        // The data for our dataset
                        data: {
                            labels: [<%=dati%>],
                            datasets: [{
                                label: 'Massa Grassa',
                                backgroundColor: 'rgb(18,255,255)',
                                borderColor: 'rgb(0,248,170)',
                                data: [<%=massagrassa%>]
                            }]
                        },
                        // Configuration options go here
                        options: {
                            layout: {
                                padding: {
                                    left: 90,/*300*/
                                    right: 0,
                                    top: 0,
                                    bottom: 0
                                }
                            }
                        }
                    });
                </script>
            </div>
        </div>

    <div class="card text-right">
        <div class="card-body">
            <p class="card-text">
            <table class="table text-center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Schede</th>
                    <th scope="col">Data</th>
                </tr>
                </thead>
                <tbody>
                <%for (int i=trainingList.size()-1;i>=0;i--){%>
                <tr>
                    <td><%=i+1%></td>
                    <td><%=trainingList.get(i).getDate()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            </p>
                <form action="createTrainingPlan.jsp?email=<%=email%>" method="post">
                    <button type="submit" class="btn btn-sm btn-outline-primary">Crea nuova scheda</button>
                </form>
    </div>
</div>
</div>

</main>

<!--  inserimento footer -->
<%@include file="footer.jsp"%>

</body>
</html>
