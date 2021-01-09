<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        $( function() {
            $( "#datepicker" ).datepicker({
                altField : "#data",
                dateFormat: "yy-mm-dd"
            });
        } );
    </script>

    <link rel="stylesheet" type="text/css" href="css/VerifyAppointments.css">
    <script src="js/verifyAppointments.js"></script>

        </head>
    <body>
        <div id="container">
            <div id="datepicker"></div>
            <input hidden type="text" id="data">
            <button id="visualizzaappuntamenti" onclick="seeAppointments()">Visualizza</button>
            <div id="giornoscelto"></div>
            <div id="appuntamenti" class="card border-dark mb-3">
                <table  id="tuttiappuntamenti" class="table">
                    <thead>
                    <tr>
                        <th scope="col">ora</th>
                        <th scope="col">Utente</th>
                    </tr>
                    </thead>
                    <tbody id="lista">
                    </tbody>
                </table>
            </div>

            <button id="inseriscidisponibilità">Programma Disponibilità</button>
        </div>
    </body>
</html>
