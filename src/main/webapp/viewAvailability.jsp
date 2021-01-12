<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visiona Agenda</title>
    <!--
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    -->
    <%@include file="meta.jsp"%>
    <%@include file="head.jsp"%>
    <%@include file="navbar.jsp"%>
    <!--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    -->

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

    <script src="js/viewAvailability.js"></script>


</head>
<body>

<main>

<!-- <div class="grid-container" align="center">

</div> -->
<div class="container">
    <div class="row justify-content-md-center">

        <div class="col">
            <div id="datepicker"></div>
        <input id="iddataSelected" hidden type="text">
            <br>
                <form>
                    <button type="button" class="btn btn-primary" id="sendDate">Visualizza disponibilità</button>
                </form>

        </div>


        <!--<div class="row justify-content-md-center">-->
            <div class="col">
            <div class="alert alert-success" role="alert" id="divSuccess"></div>
            <div class="alert alert-danger" role="alert" id="divError"></div>
            <div class="alert alert-primary" role="alert" id="divList"></div>
            <!-- <div class="col col-lg-3"></div> -->
            </div>
        </div>


</div>

</main>

<%@include file="footer.jsp"%>

</body>
</html>
