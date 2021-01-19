<%@ page import="it.unisa.c03.myPersonalTrainer.account.bean.Account" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.dao.RequiredTrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.trainingplan.service.RequiredTrainingPlanServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error1 = (String) request.getSession().getAttribute("error");
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
        RequiredTrainingPlan requireTest;
        RequiredTrainingPlanDAO requiredTrainingPlanDao = new RequiredTrainingPlanDAOImpl();
        RequiredTrainingPlanService requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDao);
        boolean checked = requiredTrainingPlanService.searchAccountByEmail(emailClientee);

    %>

    <div class="card">
        <h5 class="card-header"> <i class="fas fa-user"></i> <%=accountCustomer.getName()%> <%=accountCustomer.getSurname()%></h5>
        <h7 class="card-header">Inserisci i tuoi parametri</h7>
        <div class="card-body">
            <form id="formID" onsubmit="return stopsubmit(this);">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="idweight">Peso: </label> <label id="controlweight"></label>
                        <input class="form-control" id="idweight" onkeyup="validateWeight()" onkeyup="viewComponents()" name="weight" placeholder="Peso in kg">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idfatMass">La tua massa grassa</label> <label id="controlfatMass"></label>
                        <input class="form-control" id="idfatMass" onkeyup="validatefatMass()" onkeyup="viewComponents()" name="fatMass"
                               placeholder="in percentuale">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idleanMass">La tua massa magra</label> <label id="controlleanMass"></label>
                        <input class="form-control" id="idleanMass" onkeyup="validateLeanMass()" onkeyup="viewComponents()" name="leanMass"
                               placeholder="in percentuale">
                    </div>
                </div>
                <%if (!checked) {%>
                <button type="submit" class="btn btn-primary" id="buttonSubmit" name="action">Invia Parametri</button>
            </form>
            <% } else { %>
            <%
                requireTest = requiredTrainingPlanService.getAccountByEmail(emailClientee);
                if (requireTest.getRequired() == 1) {
            %>
            <div class="alert alert-danger" role="alert" id="divRequestedTrainingPlan">Scheda già richiesta! Attenderne la creazione</div>
            <button type="submit" class="btn btn-primary" id="buttonSubmit" name="action" disabled>Invia Parametri</button>

            </form>
            <% } else { %>
            <button type="submit" class="btn btn-primary" id="buttonSubmit" name="action">Invia Parametri</button>
            </form>
            <% }
            }%>
            &nbsp
            <form action="<%=request.getContextPath()%>/ManageRequiredTrainingPlanServlet" method="post">
                <input type="hidden" name="email" value=<%=emailClientee%>>
                <%if (!checked) {%>
                <button type="submit" id="Salva" name="action" class="btn btn-primary" value="reqtp">Richiedi Scheda</button>
                <% } else { %>
                <%
                    requireTest = requiredTrainingPlanService.getAccountByEmail(emailClientee);
                    if (requireTest.getRequired() == 1) { %>
                <button type="submit" id="Salva" name="action" class="btn btn-primary" value="reqtp" disabled>Hai già una scheda richiesta</button>
                <% } else { %>
                <button type="submit" id="Salva" name="action" class="btn btn-primary" value="reqtp">Richiedi Scheda</button>
                <% }
                }%>
            </form>
        </div>
    </div>

    <div class="alert alert-danger" role="alert" id="divError"></div>

    <div class="alert alert-success" role="alert" id="divSuccess"></div>

    <% String success = (String) request.getSession().getAttribute("success");
        if (success != null) {%>
    <div class="alert alert-success" role="alert" id="errorDiv">
        <p><%= success %>
        </p>
    </div>
    <% request.getSession().removeAttribute("success");
    }%>

    <% String alreadyRequired = (String) request.getSession().getAttribute("alreadyRequired");
        if (alreadyRequired != null) {%>
    <div class="alert alert-danger" role="alert" id="errorDiv">
        <p><%= alreadyRequired %>
        </p>
    </div>
    <% request.getSession().removeAttribute("alreadyRequired");
    }%>


    <% String firstTPSuccess = (String) request.getSession().getAttribute("firstTPSuccess");
        if (firstTPSuccess != null) {%>
    <div class="alert alert-success" role="alert" id="errorDiv">
        <p><%= firstTPSuccess %>
        </p>
    </div>
    <% request.getSession().removeAttribute("firstTPSuccess");
    }%>

</main>

<%@include file="footer.jsp"%>

</body>
<% }%>
</html>

