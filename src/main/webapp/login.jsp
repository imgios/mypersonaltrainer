<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>myPersonalTrainer | Login</title>
    <meta charset="utf-8">
    <!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"-->

    <link rel="stylesheet" href="css/style.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <%@include file="header.jsp"%>


</head>
<body>

<div class="login_form">
<div class="container">
            <div class="main_container">
                <div class="item">
                    <div class="welcomeMessage">
                         Benvenuto, accedi alla tua Area Personale
                    </div>
                </div>
                <div class="item">
                    <div class="wrapperLogin">
                        <div class="form">
                            <form method="post" action="LoginServlet" onsubmit="return validation();">
                                <div class="input_wrap">
                                    <label for="idEmail">Email: </label>
                                    <div class="input_field">
                                        <input type="text" id="idEmail" name="email" placeholder="Inserisci la tua email" required> <!-- class="input" id="input_text"-->
                                    </div>
                                </div>
                                <div class="input_wrap">
                                    <label for="idPassword">Password: </label>
                                    <div class="input_field">
                                        <input type="password" id="idPassword" name="password" placeholder="Inserisci la tua password" required> <!--class="input" id="input_password"-->
                                    </div>
                                </div>
                                <div class="input_wrap">
                                    <span class="error_msg">Incorrect username or password. Please try again</span>
                                    <input type="submit" id="login_btn" class="btn enabled" value="Accedi">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>

<!--FOOTER DA INSERIRE-->
<%@include file="footer.jsp"%>
<!--FINE FOOTER-->
</body>
</html>
