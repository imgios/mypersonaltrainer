<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String error = (String) request.getSession().getAttribute("error");
    String done = (String) request.getSession().getAttribute("done");
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="ServletParameters">Hello Servlet</a>
<form action="parameters-controller">
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
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<% if (error != null) {
%>
<h1> Errore ! <%=error%>
</h1>
<div class="alert alert-danger" role="alert">
    <%=error%>
</div>

<% }
    request.getSession().removeAttribute("error");%>
<% if (done != null) {
%>
<h1><%=done%>
</h1>
<div class="alert alert-success" role="alert">
    <%=done%>
</div>

<% }
    request.getSession().removeAttribute("done"); %>


</body>
</html>