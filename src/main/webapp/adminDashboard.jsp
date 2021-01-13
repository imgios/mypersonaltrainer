<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.service.AgendaServiceImpl" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment" %>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>

<html>
<head>
    <title>myPT | Account</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>


    <link rel="stylesheet" href="css/dashboard.css"/>

</head>
<body>

<%
    AccountDAO a_dao = new AccountDAOImpl();
    AccountService a_serv = new AccountServiceImpl(a_dao);
    Account admin_sess = a_serv.getAccountByEmail(emailAdmin);
%>

<main>

<div class="welcomeMessage">
    <h5> &nbsp; Benvenuto, <b><i><%=admin_sess.getName()%></i></b> </h5>
</div>

    <div class="content-wrapper">
        <div class="row">

            <div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Agenda di oggi</h4>

                        <%
                            AgendaDAO agendaDAO = new AgendaDAOImpl();
                            AgendaService agendaService = new AgendaServiceImpl(agendaDAO);

                            LocalDate today = LocalDate.now();
                            List<Appointment> appuntamenti = agendaService.findAppointmentByDate(today.toString());
                        %>

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Orario</th>
                                <th>Email</th>
                                <th>Nome</th>
                                <th>Cognome</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%
                                for(Appointment app : appuntamenti){
                            %>

                            <tr>
                                <td><%= app.getDate() %></td>
                                <td><%= app.getCustomerMail() %></td>
                                <td><%= app.getCustomerMail() %></td>
                                <td><%= app.getCustomerMail() %></td>
                            </tr>
                            <% } %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


            <div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Shortcut</h4>

                        <div class="row">

                            <!-- Registra Account -->
                            <div class="col-xl-6 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    <a href="./createAccount.jsp" style="color: #4e73df">Crea Account</a></div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-user-circle fa-2x"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <!-- Visualizza Agenda -->
                            <div class="col-xl-6 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    <a href="./verifyAppointments.jsp" style="color: #1cc88a"> Visualizza Agenda</a></div>

                                            </div>
                                            <div class="col-auto">
                                                <i class="far fa-calendar-alt fa-2x"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">

                            <!-- Pagamenti -->
                            <div class="col-xl-6 col-md-6 mb-4">
                                <div class="card border-left-info shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1"><a href="./viewSubscriptionList.jsp" style="color: #36b9cc">Stato Abbonamenti</a>
                                                </div>

                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-clipboard-list fa-2x"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Crea Scheda -->
                            <div class="col-xl-6 col-md-6 mb-4">
                                <div class="card border-left-warning shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                    <a href="./createTrainingPlan.jsp" style="color: #ffc107">Nuova Scheda</a></div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-bell fa-2x"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>



</main>

    <%@include file="footer.jsp"%>
</body>

<%
    }
%>
</html>
