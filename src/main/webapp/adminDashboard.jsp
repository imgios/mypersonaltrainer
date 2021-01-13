<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
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
                        <h4 class="card-title">Hoverable Table</h4>

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
                            <tr>
                                <td>Jacob</td>
                                <td>Photoshop</td>
                                <td class="text-danger"> 28.76% <i class="mdi mdi-arrow-down"></i>
                                </td>
                                <td>
                                    <label class="badge badge-danger">Pending</label>
                                </td>
                            </tr>
                            <tr>
                                <td>Messsy</td>
                                <td>Flash</td>
                                <td class="text-danger"> 21.06% <i class="mdi mdi-arrow-down"></i>
                                </td>
                                <td>
                                    <label class="badge badge-warning">In progress</label>
                                </td>
                            </tr>
                            <tr>
                                <td>John</td>
                                <td>Premier</td>
                                <td class="text-danger"> 35.00% <i class="mdi mdi-arrow-down"></i>
                                </td>
                                <td>
                                    <label class="badge badge-info">Fixed</label>
                                </td>
                            </tr>
                            <tr>
                                <td>Peter</td>
                                <td>After effects</td>
                                <td class="text-success"> 82.00% <i class="mdi mdi-arrow-up"></i>
                                </td>
                                <td>
                                    <label class="badge badge-success">Completed</label>
                                </td>
                            </tr>
                            <tr>
                                <td>Dave</td>
                                <td>53275535</td>
                                <td class="text-success"> 98.05% <i class="mdi mdi-arrow-up"></i>
                                </td>
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
