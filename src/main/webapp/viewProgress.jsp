<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.bean.Parameters" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAOImpl" %>

<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 28/12/20
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>


<%
    String utente_reg = (String) request.getSession().getAttribute("clienteMail");

    if (utente_reg == null) {
    response.sendRedirect("./error.jsp");
    } else {
%>
<!--
< %
    //String email = "mail@io.it";
    //String email = "prova@io.it";
    // String email = "test@utente.it";
    //String email_utente="prova@io.it";
% >
-->

<!--
< %
String error = (String) request.getSession().getAttribute("error");
String done = (String) request.getSession().getAttribute("done");
% >
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Progressi Cliente</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <link rel="stylesheet" href="css/viewProgress.css"/>

</head>
<body>

<%
    ParametersDAO paramDao = new ParametersDAOImpl();
    ParametersService sparam = new ParametersServiceImpl(paramDao);
    //  sparam.getByMail(email);
    ArrayList<Parameters> list = sparam.getByMail(utente_reg);
    //  for (Parameters param: list){
    //    System.out.println(param);
%>
<%
    String passaggio ="";
    String dati = "";
    String massamagra = "";
    String massagrassa = "";

    for (Parameters param: list){
        passaggio = passaggio + param.getweight() + ",";
       // System.out.println(passaggio);
        dati = dati + "'" + param.getinsertionDate()+ "'" + ",";
       // System.out.println(dati);
        massamagra = massamagra + param.getfatMass() + ",";
       // System.out.println(massamagra);
        massagrassa = massagrassa + param.getleanMass() + ",";
       // System.out.println(massagrassa);
    }
%>

<!--
< %=param.getMailClient()+" , "+param.getweight()+", "+param.getfatMass()+", "+param.getleanMass() + " ," +param.getinsertionDate()%>
< % }% >
-->

<!-- inserimento script -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<main>
    <!-- <div id = "main"> -->
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
            <!---->
        </div>


        <div>      <!-- 120px -->
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

        <!--
     </div>
         </div> -->

</main>

<%
    }
%>

<%@include file="footer.jsp"%>
</body>
</html>
