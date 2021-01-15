<%@ page import="java.util.List" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Availability" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if (emailCliente == null)
    {
        response.sendRedirect("error.jsp");
    } else {
%>

<head>
    <title>Agenda</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>



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

<main>

<div id="ricarica">
<div class="container">
    <div class="row">
        <div class="col">
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
   <!-- <button id="richiediappuntamento"  onclick="showdataform()"> Richiedi Appuntamento</button> -->
        <button class="btn btn-primary"  onclick="showdataform()"> Richiedi Appuntamento</button>
        </div>
        <div class="col">
    <div id="datepicker"></div>
        <!-- <button id="cercadisponibilità" onclick="caricaore()" > Ricerca</button> -->
    <button  id="cercadisponibilità" class="btn btn-primary" onclick="caricaore()" > Ricerca</button>
        </div>
</div>


<div class="row">
    <div id="ricaricaore">
        <div class="list-group" id="listaore" role="tablist">
        </div>
        <button class="btn btn-primary" id="prenota" onclick="prenota(<%= "'"+mail+"'"%>)" > Prenota Appuntamento</button>
    </div>
</div>
    <input id="data" hidden type="text">
    <input id="time" hidden name="time" type="text">

    <div class="alert alert-danger" role="alert"  id="errorDiv">
    </div>

    <div class="alert alert-success" role="alert"  id="SuccessDiv">
    </div>
</div>
</div>
</main>
<%@include file="footer.jsp"%>
</body>
<% }%>
</html>
