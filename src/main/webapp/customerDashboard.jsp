<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanService" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAO" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao.RequiredTrainingPlanDAOImpl" %>
<%@ page import="it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service.RequiredTrainingPlanServiceImpl" %>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
    String emailCliente = (String) request.getSession().getAttribute("clienteMail");
    if(emailCliente == null)
       // response.sendRedirect("login.jsp");
          response.sendRedirect("error.jsp");
%>

<html>
<head>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <title>myPersonalTrainer | myAccount</title>
</head>
        <div class="welcomeMessage">
            Benvenuto, Cliente
        </div>

        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="height: 100px">
                        <div class="d-flex justify-content-center">
                            <img src="images/void.png" class="img-fluid" alt="Responsive image">
                        </div>
                    </div>
                    <div class="col"><div class="d-flex justify-content-center">Column</div></div>
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="row justify-content-md-center">
                            <div class="row align-items-end">
                                <form action="<%=request.getContextPath()%>/ManageRequiredTrainingPlanServlet" method="post" onsubmit="return stopsubmit(this);">
                                    <% String email = "trainerino@testing.com";
                                        RequiredTrainingPlan requireTest;
                                        RequiredTrainingPlanDAO requiredTrainingPlanDao = new RequiredTrainingPlanDAOImpl();
                                        RequiredTrainingPlanService requiredTrainingPlanService = new RequiredTrainingPlanServiceImpl(requiredTrainingPlanDao);

                                        boolean checked = requiredTrainingPlanService.searchAccountByEmail(email);
                                        System.out.println("la mail �:"+email);
                                        System.out.println("Cheked �"+checked);
                                        if(!checked) { %>
                                    <button type="submit" class="btn btn-primary">Richiedi una Nuova Scheda</button>
                                    <% System.out.println("la mail aricontrollata �:"+email);} else {%>
                                    <%requireTest=requiredTrainingPlanService.getAccountByEmail(email);
                                        System.out.println("la mail aricontrollata �:"+email);
                                        if(requireTest.getRequired()==1) { %>
                                    <button type="submit" class="btn btn-primary" disabled>Richiedi una Nuova Scheda</button>
                                    <% } else {%>
                                    <button type="submit" class="btn btn-primary">Richiedi una Nuova Scheda</button>
                                    <% }
                                    }%>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary">Visualizza le schede precedenti</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <%@ include file="footer.jsp"%>

</body>
</html>
