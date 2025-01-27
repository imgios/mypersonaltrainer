<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    String emailClientee = (String) request.getSession().getAttribute("clienteMail");

    if(emailClientee == null)
        response.sendRedirect("error.jsp");
    else{
%>
<head>

    <title>Profilo Utente</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <!-- JavaScript -->
    <script src="js/profileScript.js"></script>
    <script src="js/controlChangePassword.js"></script>

</head>
<body>

<main>

    <div class="welcomeMessage">
        <h5> &nbsp;Gestisci Profilo </h5>
    </div>

    <div class="container">

    <div class="row">
        <div class="col-sm-4">
            <div class="list-group" id="list-tab" role="tablist">

                <button class="list-group-item list-group-item-action list-group-item-primary" id="list-profile-btn" data-bs-toggle="list" role="tab" aria-controls="profile" onclick="showProfile()">Profilo</button>
                <button class="list-group-item list-group-item-action" id="list-password-btn" data-bs-toggle="list" role="tab" aria-controls="password" onclick="showPassword()">Cambia Password</button>
                <button class="list-group-item list-group-item-action" id="list-abbonamento-btn" data-bs-toggle="list" role="tab" aria-controls="abbonamento" onclick="showAbbonamento()">Abbonamento
                    <%
                        SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
                        SubscriptionService subService = new SubscriptionServiceImpl(subscriptionDAO);
                        //check the Subscription state
                        int state = subService.checkSubscriptionState(emailClientee);
                        if(state == 1){
                    %>
                    <span class="badge rounded-pill bg-success text-white">Attivo</span></button>
                <% } else if (state == 0) {%>
                <span class="badge rounded-pill bg-warning text-white">In scadenza</span></button>
                <% } else if (state == -1) {%>
                <span class="badge rounded-pill bg-danger text-white">Scaduto</span></button>
                <% } %>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="scritta-profile" role="tabpanel">

                    <%
                        AccountDAO accountDao = new AccountDAOImpl();
                        AccountService accountService = new AccountServiceImpl(accountDao);
                        Account account = accountService.getAccountByEmail(emailClientee);
                    %>

                    <div id="testoProfilo" class="card">
                        <div class="card-body">
                            <h3>Le tue informazioni</h3>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Email</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=account.getEmail()%>" readonly>
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Nome</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=account.getName()%>" readonly>
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Cognome</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=account.getSurname()%>" readonly>
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Telefono</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=account.getPhone()%>" readonly>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

                <div class="tab-pane fade show active" id="scritta-abbonamento" role="tabpanel">

                    <div id="testoAbbonamento" class="card">
                        <div class="card-body">
                            <h3>Il tuo Abbonamento</h3>

                            <% Subscription sub = subService.searchSubscriptionByEmail(emailClientee); %>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Email</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=sub.getCustomerMail()%>" readonly>
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Scadenza</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=sub.getExpDate()%>" readonly>
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Prezzo</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1" value="<%=sub.getPrice()%>" readonly>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

                <div class="tab-pane fade show active" id="scritta-password" role="tabpanel">

                    <div id="testoPassword" class="card">
                        <div class="card-body">

                            <h3>Modifica la password</h3>

                            <form action="ChangePassword" method="post" onsubmit="return stopsubmit(this);">
                                <div class="row mb-3">
                                    <label for="email" class="col-sm-5">Email</label> <label id="controlEmail"></label>
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="email" name="email" placeholder="Inserisci la tua email" onkeyup="validateEmail()" required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="password" class="col-sm-5">Password</label> <label id="controlPassword"></label>
                                    <div class="col-sm-12">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Inserisci la nuova password" onkeyup="validatePassword()" required>
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">Modifica</button>
                            </form>

                            <!-- Div in basso per errori -->
                            <% String error = (String) request.getSession().getAttribute("errorToShow");
                                if ( error != null)
                                {%>
                            <div class="alert alert-danger" role="alert"  id="errorDiv">
                                <p><%= error %> </p>
                            </div>
                            <%}%>

                            <% String success = (String) request.getSession().getAttribute("successToShow");
                                if ( success != null)
                                {%>
                            <div class="alert alert-success" role="alert"  id="errorDiv">
                                <p><%= success %> </p>
                            </div>
                            <%}%>

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
<% }%>
</html>