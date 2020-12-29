<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo Utente</title>

    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- JavaScript -->
    <script src="js/profileScript.js"></script>
    <script src="js/controlChangePassword.js"></script>


</head>
<body>

<div>
    <h1>Gestisci Profilo</h1>
</div>

<div class="container">

    <div class="row">
        <div class="col-sm-4">
            <div class="list-group" id="list-tab" role="tablist">

                <button class="list-group-item list-group-item-action active" id="list-profile-btn" data-bs-toggle="list" role="tab" aria-controls="profile" onclick="showProfile()">Profilo</button>
                <button class="list-group-item list-group-item-action" id="list-password-btn" data-bs-toggle="list" role="tab" aria-controls="password" onclick="showPassword()">Cambia Password</button>
                <button class="list-group-item list-group-item-action" id="list-abbonamento-btn" data-bs-toggle="list" role="tab" aria-controls="abbonamento" onclick="showAbbonamento()">Abbonamento</button>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="scritta-profile" role="tabpanel">

                    <div id="testoProfilo" class="card">
                        <div class="card-body">
                            <h3>Le tue informazioni</h3>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Email</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Nome</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Cognome</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Telefono</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>


                        </div>

                    </div>

                </div>

                <div class="tab-pane fade show active" id="scritta-abbonamento" role="tabpanel">

                    <div id="testoAbbonamento" class="card">
                        <div class="card-body">
                            <h3>Il tuo Abbonamento</h3>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Email</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Scadenza</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>

                            <div class="input-group mb-3">
                                <label class="col-sm-5">Prezzo</label>
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" aria-label="Phone" aria-describedby="basic-addon1">
                                </div>
                            </div>




                        </div>

                    </div>

                </div>

                <div class="tab-pane fade show active" id="scritta-password" role="tabpanel">

                    <div id="testoPassword" class="card">
                        <div class="card-body">

                            <h3>Modifica la password</h3>

                            <form action="ChangePassword" method="post" onsubmit="return stopsubmit(this);">
                                <div class="row mb-3">
                                    <label for="email" class="col-sm-5">Email</label> <label id="controlEmail"></label>
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="email" name="email" placeholder="Inserisci la tua email" onkeyup="validateEmail()" required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="password" class="col-sm-5">Password</label> <label id="controlPassword"></label>
                                    <div class="col-sm-12">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Inserisci la nuova password" onkeyup="validatePassword()" required>
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">Cambia</button>
                            </form>

                            <!-- Div in basso per errori -->
                            <% String error = (String) request.getSession().getAttribute("errorToShow");
                                if ( error != null)
                                {%>
                            <div class="alert alert-danger" role="alert"  id="errorDiv">
                                <p><%= error %> </p>
                            </div>
                            <%}%>

                            <% String success = (String) request.getSession().getAttribute("successToShow");
                                if ( success != null)
                                {%>
                            <div class="alert alert-success" role="alert"  id="errorDiv">
                                <p><%= success %> </p>
                            </div>
                            <%}%>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>