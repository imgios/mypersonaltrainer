<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Invia i parametri</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="js/insertParameters.js"></script>
</head>
<body>

<form>
    <div class="form-group">
        <label for="idweight">Il tuo weight</label>
        <input class="form-control" id="idweight" name="weight" placeholder="Enter weight">
    </div>
    <div class="form-group">
        <label for="idfatMass">massa grassa</label>
        <input class="form-control" id="idfatMass" name="fatMass">
    </div>
    <div class="form-group">
        <label for="idleanMass">massa magra</label>
        <input class="form-control" id="idleanMass" name="leanMass">
    </div>
    <button type="button" id="buttonSubmit" class="btn btn-primary">Submit</button>
</form>

<div class="alert alert-danger" role="alert" id="divError"></div>

<div class="alert alert-success" role="alert" id="divSuccess"></div>

</body>
</html>