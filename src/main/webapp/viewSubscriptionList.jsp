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


    <title>Abbonamenti</title>


    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>



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

<main>

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
                                    Account a = accountService.getAccountByEmail(s.getCustomerMail());
                                    if(a == null)
                                    {
                                %>
                                <tr>
                                    <td>Account non trovato</td>
                                </tr>
                                <% } else{ %>
                                <tr>
                                    <td><%=a.getName()%></td>
                                    <td><%=a.getSurname()%></td>
                                    <td><%=s.getCustomerMail()%></td>
                                    <td><%=s.getExpDate()%></td>
                                </tr>
                                <%}
                                }%>
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
                                    Account a = accountService.getAccountByEmail(s.getCustomerMail());
                                    if(a == null)
                                    {
                                %>
                                <tr>
                                    <td>Account non trovato</td>
                                </tr>
                                <% } else{ %>
                                <tr>
                                    <td><%=a.getName()%></td>
                                    <td><%=a.getSurname()%></td>
                                    <td><%=s.getCustomerMail()%></td>
                                    <td><%=s.getExpDate()%></td>
                                </tr>
                                <%}
                                }%>
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
                                    Account a = accountService.getAccountByEmail(s.getCustomerMail());
                                    if(a == null)
                                    {
                                %>
                                <tr>
                                    <td>Account non trovato</td>
                                </tr>
                                <% } else{ %>
                                <tr>
                                    <td><%=a.getName()%></td>
                                    <td><%=a.getSurname()%></td>
                                    <td><%=s.getCustomerMail()%></td>
                                    <td><%=s.getExpDate()%></td>
                                </tr>
                                <%}
                                }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</main>


<%@include file="footer.jsp"%>


</body>
</html>


