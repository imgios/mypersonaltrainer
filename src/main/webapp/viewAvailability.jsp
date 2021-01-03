
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visiona Agenda</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="js/viewAvailability.js"></script>

</head>
<body>

<form action="view-availability">
    <div class="form-group">
        <label for="iddataSelected">Data desiderata:</label>
        <input type="date" id="iddataSelected" name="dataSelected">
    </div>
    <button type="button" id="sendDate">Manda</button>
</form>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="row justify-content-md-center">
            <div class="col col-lg-3">
            </div>
            <div class="alert alert-success" role="alert" id="divSuccess"></div>

            <div class="col col-lg-3">
            </div>
        </div>
    </div>
</div>
<div class="alert alert-danger" role="alert" id="divError"></div>

<!--<div class="alert alert-success" role="alert" id="divSuccess"></div> -->
</body>
</html>
