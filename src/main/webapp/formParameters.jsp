<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Invia i parametri</title>
    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    -->

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>



    <script src="js/insertParameters.js"></script>
</head>
<body>

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



<%@include file="footer.jsp"%>

</body>
</html>