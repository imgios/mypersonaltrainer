<%--
  Created by IntelliJ IDEA.
  User: giampieroferrara
  Date: 25/12/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>

    <!--
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    -->

    <%@include file="./meta.jsp"%>
   <!--
    < %@include file="./head.jsp"%>
    -->

    <link href="css/footer.css" rel="stylesheet" type="text/css">

</head>
<body>

<!-- footer -->
<footer class="footer">

    <nav class="navbar navbar-default navbar-custom_footer">

        <div class="container-fluid">

            <div class="nav justify-content-center">
                <div class="row text-center">
                    <div class="col-lg-12"> <h5 class="font-weight-bold">myPersonalTrainer</h5> </div>
                    <div class="col-lg-12"> <p class="font-weight-light"> Software Project Management and Software Engineering -- credits 2020</p> </div>
                </div>
                <ul class="nav flex-column">
                    <li>
                        <a href="https://github.com/imgios/mypersonaltrainer">Github</a>
                    </li>
                    <!--
                    <li>
                        <a href="">Link 2</a>
                    </li>
                    <li>
                        <a href="">Link 3</a>
                    </li>
                    -->
                </ul>
            </div>
<!-- social se vogliamo
            <div class="text-center center-block">
                <a href="google.it"><i id="social-fb" class="fa fa-facebook-square fa-3x social"></i></a>
                <a href="google.it"><i id="social-tw" class="fa fa-twitter-square fa-3x social"></i></a>
                <a href="google.it"><i id="social-gp" class="fa fa-google-plus-square fa-3x social"></i></a>
                <a href=""><i id="social-em" class="fa fa-envelope-square fa-3x social"></i></a>
            </div>

            <div class="nav navbar-nav navbar-right">
                <ul>
                    <li>
                        <a href="">Link 4</a>
                    </li>
                    <li>
                        <a href="">Link 5</a>
                    </li>
                    <li>
                        Text
                    </li>
                </ul>
            </div>
            -->
        </div>

    </nav>
</footer>

<!-- script bootstrap
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
-->
</body>
</html>
