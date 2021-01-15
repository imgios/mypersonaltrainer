<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String emailClientee = (String) request.getSession().getAttribute("clienteMail");

    if(emailClientee == null)
        response.sendRedirect("error.jsp");
    else{
%>

<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
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

    <div class="welcomeMessage">
        <h5> &nbsp;Parametri fisici</h5>
    </div>

<main>
<form onsubmit="return stopsubmit(this);">
    <div class="form-group">
        <label for="idweight">Il tuo peso</label> <label id="controlweight"></label>
        <input class="form-control" id="idweight" onkeyup="validateWeight()" name="weight" placeholder="in kg">
    </div>
    <div class="form-group">
        <label for="idfatMass">La tua massa grassa</label> <label id="controlfatMass"></label>
        <input class="form-control" id="idfatMass" onkeyup="validatefatMass()" name="fatMass"
               placeholder="in percentuale">
    </div>
    <div class="form-group">
        <label for="idleanMass">La tua massa magra</label> <label id="controlleanMass"></label>
        <input class="form-control" id="idleanMass" onkeyup="validateLeanMass()" name="leanMass"
               placeholder="in percentuale">
    </div>
    <button type="button" id="buttonSubmit" class="btn btn-primary">Submit</button>
</form>

<div class="alert alert-danger" role="alert" id="divError"></div>

<div class="alert alert-success" role="alert" id="divSuccess"></div>


</main>

<%@include file="footer.jsp"%>

</body>
<% }%>
</html>