<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters" %><%--
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
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    ParametersService sparam = new ParametersServiceImpl();
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

%>
<!--
<h1>ci sono i parametri nella console da stampare</h1>
< %=param.getMailClient()+" , "+param.getweight()+", "+param.getfatMass()+", "+param.getleanMass() + " ," +param.getinsertionDate()%>
< % }% >
-->
<!-- inserimento script -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<div class="grid-container">
    <div>
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
    backgroundColor: 'rgb(255, 99, 132)',
    borderColor: 'rgb(255, 99, 132)',
    data: [<%=passaggio%>]

    }]
    },

    // Configuration options go here
    options: {}
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
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: [<%=massamagra%>]

                }]
            },
            // Configuration options go here
            options: {}
        });
    </script>
    </div>
</div>


    <div>
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
                            backgroundColor: 'rgb(255, 99, 132)',
                            borderColor: 'rgb(255, 99, 132)',
                            data: [<%=massagrassa%>]

                        }]
                    },
                    // Configuration options go here
                    options: {}
                });
            </script>
        </div>
    </div>
</div>
</div>

<!--  inserimento footer -->


</body>
<%@ include file="footer.jsp"%>
</html>
