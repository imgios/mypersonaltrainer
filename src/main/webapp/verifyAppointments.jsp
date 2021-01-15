<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>

<head>
    <title>Controllo Appuntamenti</title>

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
<%
    }
%>
</html>
