
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo Utente</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div>
    <h1>Inserisci qui la nuova Password</h1>
</div>

<div>
<form action="profiloutente-servlet" method="post">
    <div class="row mb-3">
        <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="inputEmail">
        </div>
    </div>
    <div class="row mb-3">
        <label>Password</label>
        <input id="passwordR" type="password" name="passwordR" placeholder="enter password" class="form-control">
    </div>


    <button type="submit" class="btn btn-primary">Cambia</button>
</form>
</div>

</body>
</html>
