<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<form action="ServletParameters">
    <div class="form-group">
        <label for="idpeso">Il tuo pesooo</label>
        <input type="number" class="form-control" id="idpeso"  name="peso" placeholder="Enter peso">
    </div>
    <div class="form-group">
        <label for="idmassaGrassa">massa grassa</label>
        <input type="number" class="form-control" id="idmassaGrassa" name="massaGrassa">
    </div>
    <div class="form-group">
        <label for="idmassaMagra">massa grassa</label>
        <input type="number" class="form-control" id="idmassaMagra" name="massaMagra">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>