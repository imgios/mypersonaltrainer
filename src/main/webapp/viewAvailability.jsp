<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String emailAdmin = (String) request.getSession().getAttribute("ptMail");
    if(emailAdmin == null) {
        response.sendRedirect("error.jsp");
    } else {
%>
<head>
    <title>Visiona Agenda</title>

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

    <script src="js/viewAvailability.js"></script>


</head>
<body>

<main>

    <div class="welcomeMessage">
        <h5> &nbsp;Visualizza Disponibilità</h5>
    </div>

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
<%
    }
%>
</html>
