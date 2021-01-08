<%--
  Created by IntelliJ IDEA.
  User: em
  Date: 29/12/2020
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuova Scheda Allenamento</title>
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

    <script src="js/insertTrainingPlan.js"></script>
</head>
<body>

<form id="formID" action="createTP-controller" onsubmit="return stopsubmit(this);">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="idexercise">exercise</label><label id="controlexercise"></label>
            <input class="form-control" id="idexercise" onkeyup="validateNameEx()" name="exercise">
        </div>
        <div class="form-group col-md-6">

            <label for="idrepetitions">repetitions</label><label id="controlrepetitions"></label>
            <input class="form-control" id="idrepetitions" onkeyup="validaterepetitions()" name="repetitions">
        </div>
        <div class="form-group col-md-6">

            <label for="idseries">number of series</label><label id="controlseries"></label>
            <input class="form-control" id="idseries" onkeyup="validateSeries()" name="series">
        </div>
        <div class="form-group col-md-6">

            <label for="idrecoveryTime">recoveryTime in second</label><label id="controlrecoveryTime"></label>
            <input class="form-control" id="idrecoveryTime" name="recoveryTime" onkeyup="validaterecoveryTime()" placeholder="in secondi">
        </div>

    </div>
    <button type="submit" name="action" value="addex">Invia Esercizio</button>
</form>

<form action="createTP-controller">

    <button type="submit" id="Salva" name="action" value="addtp">Crea Scheda</button>

</form>

<!-- Div in basso per errori -->
<% String error = (String) request.getSession().getAttribute("error");
    if (error != null) {%>
<div class="alert alert-danger" role="alert" id="errorDiv">
    <p><%= error %>
    </p>
</div>
<% request.getSession().removeAttribute("error");
}%>

<% String success = (String) request.getSession().getAttribute("success");
    if (success != null) {%>
<div class="alert alert-success" role="alert" id="errorDiv">
    <p><%= success %>
    </p>
</div>
<% request.getSession().removeAttribute("success");
}%>


<% String noEx = (String) request.getSession().getAttribute("noEx");
    if (noEx != null) {%>
<div class="alert alert-danger" role="alert" id="errorDiv">
    <p><%= noEx %>
    </p>
</div>
<% request.getSession().removeAttribute("noEx");
}%>


<%@include file="footer.jsp"%>

</body>
</html>