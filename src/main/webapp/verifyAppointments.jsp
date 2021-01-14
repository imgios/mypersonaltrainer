<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controllo Appuntamenti</title>
    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    -->
    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <link rel="stylesheet" type="text/css" href="./css/verifyAppointments.css">
    <script src="js/verifyAppointments.js"></script>

    <script>
        $( function() {
            $( "#datepicker" ).datepicker({
                altField : "#data",
                dateFormat: "yy-mm-dd"
            });
        } );
    </script>

        </head>

    <body>

    <main>
        <div class="alert alert-success" role="alert" id="success-alert"><p>Appuntamento cancellato con successo</p></div>
        <div class="container" >
            <div class="row">
                <div class="col">
            <div id="datepicker"></div>
                    <br>
            <input hidden type="text" id="data">
                    <button class="btn btn-primary"  onclick="seeAppointments()">Visualizza</button>
                    <button class="btn btn-primary">Programma Disponibilità</button>
                    <button class="btn btn-primary">Visualizza Disponibilità</button>
                    </div>
            <!-- <button id="visualizzaappuntamenti" onclick="seeAppointments()">Visualizza</button>-->
                <div class="col">
            <div id="giornoscelto"></div>
            <div id="appuntamenti" class="card border-dark mb-3">
                <table  id="tuttiappuntamenti" class="table">
                    <thead>
                    <tr>
                        <th scope="col">ora</th>
                        <th scope="col">Utente</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                        <tbody id="lista">
                    </tbody>
                </table>
            </div>
                </div>
            <!--<button id="inseriscidisponibilità">Programma Disponibilità</button>
                <div class="col">
                    <button class="btn btn-primary">Programma Disponibilità</button>
                </div>-->
            </div>
        </div>

    </main>

    <%@include file="footer.jsp"%>

    </body>
</html>
