<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.service.SubscriptionServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Abbonamenti</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>

<%
    SubscriptionDAO subDao = new SubscriptionDAOImpl();
    SubscriptionService subService = new SubscriptionServiceImpl(subDao);

    //lista tabella attivi
    ArrayList<Subscription> activeList = subService.getActiveSubscriptions();

    //lista tabella in scadenza
    ArrayList<Subscription> expiringList = subService.getExpiringSubscriptions();

    //lista tabella scaduti
    ArrayList<Subscription> expiredList = subService.getExpiredSubscriptions();

    AccountDAO accountDao = new AccountDAOImpl();
    AccountService accountService = new AccountServiceImpl(accountDao);

%>

<div class="bs-example">
    <div class="accordion" id="accordionExample">

        <div class="card">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                    <button type="button" class="btn btn-link" data-toggle="collapse" data-target="#collapseOne">Abbonamenti attivi</button>
                </h2>
            </div>
            <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="table-responsive-sm">
                    <table class="table table table-success table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Nome</th>
                            <th scope="col">Cognome</th>
                            <th scope="col">Email</th>
                            <th scope="col">Scadenza</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% for(Subscription s : activeList) {
                            Account a = accountService.getAccountByEmail(s.getCustomerMail()) ;%>
                        <tr>
                            <td><%=a.getName()%></td>
                            <td><%=a.getSurname()%></td>
                            <td><%=s.getCustomerMail()%></td>
                            <td><%=s.getExpDate()%></td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>



        <div class="card">
            <div class="card-header" id="headingTwo">
                <h2 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo">Abbonamenti in scadenza</button>
                </h2>
            </div>
            <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="table-responsive-sm">
                    <table class="table table table-warning table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Nome</th>
                            <th scope="col">Cognome</th>
                            <th scope="col">Email</th>
                            <th scope="col">Scadenza</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% for(Subscription s : expiringList) {
                            Account a = accountService.getAccountByEmail(s.getCustomerMail()) ;%>
                        <tr>
                            <td><%=a.getName()%></td>
                            <td><%=a.getSurname()%></td>
                            <td><%=s.getCustomerMail()%></td>
                            <td><%=s.getExpDate()%></td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree">Abbonamenti scaduti</button>
                </h2>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="table-responsive-sm">
                    <table class="table table table-danger table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Nome</th>
                            <th scope="col">Cognome</th>
                            <th scope="col">Email</th>
                            <th scope="col">Scadenza</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% for(Subscription s : expiredList) {
                            Account a = accountService.getAccountByEmail(s.getCustomerMail()) ;%>
                        <tr>
                            <td><%=a.getName()%></td>
                            <td><%=a.getSurname()%></td>
                            <td><%=s.getCustomerMail()%></td>
                            <td><%=s.getExpDate()%></td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>


