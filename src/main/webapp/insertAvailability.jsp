
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Calendar</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="js/insertAvailability.js"></script>
</head>
<body>

<form>
    <div class="form-group">
        <label for="iddataSelected">Data desiderata:</label>
        <input type="date" id="iddataSelected" name="dataSelected">
    </div>
    <div class="form-group">
        <label for="idtimeSelected">Orario desiderato:</label>
        <input type="number" min="9" max="19" name="timeSelected" id="idtimeSelected" placeholder="9">
    </div>
    <button type="button" id="buttonSubmit">Inserisci Disponibilita'</button>
</form>

<div class="alert alert-danger" role="alert" id="divError"></div>

<div class="alert alert-success" role="alert" id="divSuccess"></div>

</body>
</html>
