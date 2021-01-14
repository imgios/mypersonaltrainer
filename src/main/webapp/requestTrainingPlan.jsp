<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>
<%
    String emailClientee = (String) request.getSession().getAttribute("clienteMail");

    if(emailClientee == null)
        response.sendRedirect("login.jsp");
    else{
%>
<!DOCTYPE html>
<html>
<head>

    <title>Invia i parametri</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <script src="js/insertParameters.js"></script>
</head>
<body>

<main>

    <%
        Account accountCustomer;
        AccountDAO accountDao=new AccountDAOImpl();
        AccountService serviceAccount = new AccountServiceImpl(accountDao);

        accountCustomer=serviceAccount.getAccountByEmail(emailClientee);
    %>

    <div class="card">
        <h5 class="card-header"> <i class="fas fa-user"></i> <%=accountCustomer.getName()%> <%=accountCustomer.getSurname()%></h5>
        <h7 class="card-header">Inserisci i tuoi parametri</h7>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/ManageRequiredTrainingPlanServlet" method="post" onsubmit="return stopsubmit(this);">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="idweight">Peso: </label> <label id="controlweight"></label>
                        <input class="form-control" id="idweight" onkeyup="validateWeight()" name="weight" placeholder="Peso in kg">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idfatMass">La tua massa grassa</label> <label id="controlfatMass"></label>
                        <input class="form-control" id="idfatMass" onkeyup="validatefatMass()" name="fatMass"
                               placeholder="in percentuale">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="idleanMass">La tua massa magra</label> <label id="controlleanMass"></label>
                        <input class="form-control" id="idleanMass" onkeyup="validateLeanMass()" name="leanMass"
                               placeholder="in percentuale">
                    </div>
                </div>
                <div>
                    <button type="submit" id="buttonSubmit" class="btn btn-primary">Invia Dati e Richiedi Scheda</button>
                </div>
            </form>
        </div>
    </div>

</main>

<%@include file="footer.jsp"%>

</body>
<% }%>
</html>
