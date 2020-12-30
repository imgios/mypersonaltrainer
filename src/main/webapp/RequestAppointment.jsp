<%@ page import="java.util.List" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl" %><%--
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
    <link rel="stylesheet" type="text/css" href="appointment.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="Requestappointment.js"></script>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.1.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<div id="container">
<div id="listaappuntamento" class="card border-dark mb-3">
    <%
        AgendaDAO dao= new AgendaDAOImpl();
        AgendaService service =new AgendaServiceImpl(dao);
        String mail="prova@gmail.com";

        List<Appointment> list=dao.findAppointmetsByEmail("prova@gmail.com");%>
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
        <td><button onclick="deleteappointmentfromDB(<%= "'"+a.getDate()+"'"%>,<%= "'"+a.getTime()+"'"%>,<%="'"+mail+"'"%>)"><img src="img/cestino.png"  width="25px" height="25px"></button></td>

    </tr>
    <%}%>
    </tbody>
</table>
</div>
</div>
<div class="footer"><%@include file="footer.jsp"%></div>
</body>
</html>
