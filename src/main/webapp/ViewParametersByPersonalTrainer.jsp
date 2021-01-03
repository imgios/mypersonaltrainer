<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %><%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 28/12/20
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>

<!-- check if the user is log-->
<%
    //String client_email = (String) request.getSession().getAttribute("email");
    //System.out.println(client_email);
    // if (client_email == null){
    //  response.sendRedirect("index.jsp");   //login page che non ho al momento
    //}
    //String email = "mail@io.it";
    String email = "prova@io.it";
    // String email = "test@utente.it";
%>
<%  //check of the parameters
    //devo prendere i parametri dalla sessione?
%>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Progressi Cliente</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    ParametersDAO paramDao = new ParametersDAOImpl();
    ParametersService sparam = new ParametersServiceImpl(paramDao);
    //  sparam.getByMail(email);
    ArrayList<Parameters> list = sparam.getByMail(email);
    //  for (Parameters param: list){
    //    System.out.println(param);
%>
<% String passaggio ="";
    String dati = "";
    String massamagra = "";
    String massagrassa = "";
    for (Parameters param: list){
        passaggio = passaggio + param.getweight() + ",";
        System.out.println(passaggio);
        dati = dati + "'" + param.getinsertionDate()+ "'" + ",";
        System.out.println(dati);
        massamagra = massamagra + param.getfatMass() + ",";
        System.out.println(massamagra);
        massagrassa = massagrassa + param.getleanMass() + ",";
        System.out.println(massagrassa);
    }
    Account accountCustomer = new Account("Gino", "Diamante", "1234567898", "prova@io.it", "Ciaotutti5!", 0);
%>
<!--
<h1>ci sono i parametri nella console da stampare</h1>
< %=param.getMailClient()+" , "+param.getweight()+", "+param.getfatMass()+", "+param.getleanMass() + " ," +param.getinsertionDate()%>
< % }% >
-->
<!-- inserimento script -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<div id = "main">
<!--
    <form>
        <legend>Informazioni: </legend>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Name</label>
                <input type="email" class="form-control" value="< %=accountCustomer.getName()%>" disabled>
            </div>
            <div class="form-group col-md-6">
                <label>Surname</label>
                <input type="email" class="form-control" value="< %=accountCustomer.getSurname()%>" disabled>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Email</label>
                <input type="email" class="form-control" value="< %=accountCustomer.getEmail()%>" disabled>
            </div>
            <div class="form-group col-md-6">
                <label>Phone</label>
                <input type="email" class="form-control" value="< %=accountCustomer.getPhone()%>" disabled>
            </div>
        </div>
    </form>
-->
    <div class="card">
        <h5 class="card-header"> <img src="images/user1.png"> <%=accountCustomer.getName()%> <%=accountCustomer.getSurname()%> <br></h5>
        <h7 class="card-header"> Informazioni</h7>
        <div class="card-body">
            <p class="card-text">-->
            <table class="table text-center">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%=accountCustomer.getName()%></td>
                <td><%=accountCustomer.getSurname()%></td>
                <td><%=accountCustomer.getEmail()%></td>
                <td><%=accountCustomer.getPhone()%></td>
            </tr>
            </tbody>
        </table>
        </p>
        </div>
    </div>
<!--
<div class="d-flex justify-content-justify">

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>< %=accountCustomer.getName()%></td>
            <td>< %=accountCustomer.getSurname()%></td>
            <td>< %=accountCustomer.getEmail()%></td>
            <td>< %=accountCustomer.getPhone()%></td>
        </tr>
        </tbody>
    </table>
</div>
-->
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

        <!-- inserimento secondo diagramma massa grassa -->
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


        <div>        <!-- 120px -->
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
                <tr>
                    <td>< %=trainingPlan.getNome?()%></td>
                    <td>< %=trainingPlan.getDate()%></td>
                </tr>
                <tr>
                    <td>< %=trainingPlan.getNome?()%></td>
                    <td>< %=trainingPlan.getDate()%></td>
                </tr>
                <tr>
                    <td>< %=trainingPlan.getNome?()%></td>
                    <td>< %=trainingPlan.getDate()%></td>
                </tr>
                </tbody>
            </table>
            </p>
            <a href="#" class="btn btn-primary">Crea Nuova Scheda</a>
        </div>
    </div>
</div>
<!--  inserimento footer -->
<!-- <div id="footer"> -->
<%@ include file="footer/footer.jsp"%>
<!--</div> -->
</body>

</html>