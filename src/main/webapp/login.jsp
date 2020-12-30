<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!- inserire verifica che l'utente sia PT
isPT = true allora procedi, altrimenti errore. >
<html>
<head>
    <title>myPersonalTrainer | Login</title>
    <!-- Bootstrap provvisorio -->
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/styles.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--link rel="stylesheet" href="styles/reset.min.css" /-->
    <!--link rel="stylesheet" href="styles/style.css" /-->
    <!--link rel="stylesheet" href="styles/header-11.css" />
    <!link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<!--header class="site-header">
    <div class="nav justify-content-center">
        <a href="#" class="brand"><img src="images\nlogo.png"></a>
    </div>
</header-->
<div id= "header">
<%@include file="header.jsp"%>
    <div class="wrapper">
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
                                    <label for="idEmail">Email </label>
                                    <div class="input_field">
                                        <input type="text" id="idEmail" name="email" placeholder="Inserisci la tua email" required> <!-- class="input" id="input_text"-->
                                    </div>
                                </div>
                                <div class="input_wrap">
                                    <label for="idPassword">Password </label>
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
<!--
                    <div class="loginForm" align="center">
                      <form action="LoginServlet" method="post">
                         <div class="row mb-3" align = "left">
                           <label for="idEmail" class="col-sm-2 col-form-label">Email</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="idEmail" name="email" placeholder="Inserisci la tua email" required>
                              </div>
                         </div>
                         <div class="row mb-3" align = "left">
                         <label for="idPassword" class="col-sm-2 col-form-label">Password</label>
                          <div class="col-sm-10">
                           <input type="password" class="form-control" id="idPassword" name="password" placeholder="Inserisci la tua password" required>
                          </div>
                        </div>
                        <div align="left">
                       <button type="submit" class="btn btn-primary">Accedi</button>
                        </div> -->
                                    </form>
                        </div>
                </div>
                </div>
            </div>
    </div>
</div>
<!--
<div class="welcomeMessage" align= "left"> <h5>Benvenuto, accedi alla tua Area Personale</h5> </div>
<div class="container" align="center">
<div class= "col-7">
<div id="testoPassword" class="card">
    <div class="card-body">
            <form action="LoginServlet" method="post">
                 <div class="row mb-3" align = "left">
                     <label for="idEmail" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="idEmail" name="email" placeholder="Inserisci la tua email" required>
                        </div>
                </div>
                <div class="row mb-3" align = "left">
                     <label for="idPassword" class="col-sm-2 col-form-label">Password</label>
                     <div class="col-sm-10">
                     <input type="password" class="form-control" id="idPassword" name="password" placeholder="Inserisci la tua password" required>
                     </div>
                </div>
                <div align="center">
                    <button type="submit" class="btn btn-primary">Accedi</button>
                </div>
            </form>
    </div>
</div>
</div>
</div>
-->

<div>
    <%@include file="footer.jsp"%>
</div>
</body>
</html>

