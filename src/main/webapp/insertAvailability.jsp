<!DOCTYPE html>
<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>
<head>
    <title>Inserisci Disponibilita</title>

    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#datepicker").datepicker({
                altField: "#iddataSelected",
                dateFormat: "yy-mm-dd"
            });
        });
    </script>

     <script src="js/insertAvailability.js"></script>
</head>
<body>

<main>

    <div class="welcomeMessage">
        <h5> &nbsp;Inserisci Disponibilita'</h5>
    </div>


<br>
<br>
    <div class = "container">
        <div class="row justify-content-md-center">

            <div class="col">
            <form>
                <div id="datepicker"></div>
                <div class="form-group">
                    <label for="idtimeSelected">Orario desiderato:</label>
                    <input type="number" min="9" max="19" name="timeSelected" id="idtimeSelected" placeholder="9">
                </div>
                <button type="button" class="btn btn-primary" id="buttonSubmit">Inserisci Disponibilita'</button>
                <input id="iddataSelected" hidden type="text">
            </form>
            </div>
<br>
<br>
            <div class="col">
            <div class="alert alert-success" role="alert" id="divSuccess"></div>
            <div class="alert alert-danger" role="alert" id="divError"></div>
            </div>
        </div>
    </div>

</main>

<%@include file="footer.jsp"%>

</body>
<%
    }
%>
</html>
