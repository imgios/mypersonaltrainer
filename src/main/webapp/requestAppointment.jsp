<%@ page import="java.util.List" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Availability" %><%--
  Created by IntelliJ IDEA.
  User: Umberto
  Date: 29/12/2020
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Agenda</title>

    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    -->



    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker({
                altField : "#data",
                dateFormat: "yy-mm-dd"
            });
        } );

        function savetime(ele)
        {
            document.getElementById('time').value=document.getElementById(ele.id).innerHTML;
        }
    </script>
    <link rel="stylesheet" type="text/css" href="./css/appointment.css">
    <script src="js/requestAppointment.js"></script>
</head>
<body>
<div id="ricarica">
<div class="conteiner" id="container">
<div id="listaappuntamento" class="card border-dark mb-3">
    <%
        AgendaDAO dao= new AgendaDAOImpl();
        AgendaService service =new AgendaServiceImpl(dao);
        String mail= (String) request.getSession().getAttribute("clienteMail");

        List<Appointment> list=dao.findAppointmetsByEmail(mail);%>
<table  id="appuntamenti" class="table">
    <thead>
    <tr>
        <th scope="col">Data</th>
        <th scope="col">ora</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <% for (Appointment a : list) {%>
    <tr>
        <td><%= a.getDate()%> </td>
        <td><%= a.getTime()%> </td>
        <td><button onclick="deleteappointmentfromDB(<%= "'"+a.getDate()+"'"%>,<%= "'"+a.getTime()+"'"%>,<%="'"+mail+"'"%>)"><i class="far fa-trash-alt"></i></button></td>

    </tr>
    <%}%>
    </tbody>
</table>

</div>
    <button id="richiediappuntamento"  onclick="showdataform()"> Richiedi Appuntamento</button>
    <div id="datepicker"></div>
    <button id="cercadisponibilità" onclick="caricaore()" > Ricerca</button>

</div>

<div>
    <div id="ricaricaore">
        <div class="list-group" id="listaore" role="tablist">

        </div>
        <button id="prenota" onclick="prenota(<%= "'"+mail+"'"%>)" > Prenota Appuntamento</button>
    </div>
    <input  id="data" hidden type="text">
    <input id="time" hidden name="time" type="text">


    <div class="alert alert-danger" role="alert"  id="errorDiv">
    </div>

    <div class="alert alert-success" role="alert"  id="SuccessDiv">
    </div>
</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
