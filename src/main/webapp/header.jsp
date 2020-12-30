<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>myPersonalTrainer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<%int validSession=1;%>
<%int role=1;%>
<%if(validSession==0) { %>  <!--if(session.getAttribute("user")!=null) { %>-->
<!--response.sendRedirect("login.jsp");-->
<div class="wrapper">
    <div class="top_navbar">
        <div class="hamburger">
            <div class="one"></div>
            <div class="two"></div>
            <div class="three"></div>
        </div>
        <div class="top_menu">
            <div class="logo"><img class="responsive_logo" src="images/void.png">
            </div>
            <div class="logo"><img class="responsive_logo" src="images/nlogo.png">
            </div>
            <div>
                <img class="responsive_item_account" src="images/user1.png">
                <ul>
                    <li><a href="#"> Logout </a></li>
                    <li>&nbsp</li>
                    <li><a href="#"> Profilo Utente </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="sidebar">
        <ul>
            <li><a href="#">
                <span class="title"> </span></a></li>
            <li><a href="#">
                <span class="title"> </span>
            </a></li>
            <li><a href="#">
                <span class="title"> </span>
            </a></li>
            <li><a href="#">
                <span class="title"> </span>
            </a></li>
        </ul>
    </div>
<%} else if (role==1){%>
<div class="wrapper">
    <div class="top_navbar">
        <div class="hamburger">
            <div class="one"></div>
            <div class="two"></div>
            <div class="three"></div>
        </div>
        <div class="top_menu">
            <div class="logo"><img class="responsive_logo" src="images/void.png">
            </div>
            <div class="logo"><img class="responsive_logo" src="images/nlogo.png">
            </div>
            <div>
                <img class="responsive_item_account" src="images/user1.png">
                <ul>
                    <li><a href="#"> Logout </a></li>
                    <li>&nbsp</li>
                    <li><a href="#"> Profilo Utente </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="sidebar">
        <ul>
            <li><a href="#">
                <span class="title">Dashboard</span></a></li>
            <li><a href="#">
                <span class="title">Statistiche</span>
            </a></li>
            <li><a href="#">
                <span class="title">Agenda</span>
            </a></li>
            <li><a href="#">
                <span class="title">Cronologia Schede</span>
            </a></li>
        </ul>
    </div>
    <%}else if(role==1){%>
    <div class="wrapper">
        <div class="top_navbar">
            <div class="hamburger">
                <div class="one"></div>
                <div class="two"></div>
                <div class="three"></div>
            </div>
            <div class="top_menu">
                <div class="logo"><img class="responsive_logo" src="images/void.png">
                </div>
                <div class="logo"><img class="responsive_logo" src="images/nlogo.png">
                </div>
                <div>
                    <img class="responsive_item_account" src="images/user1.png">
                    <ul>
                        <li><a href="#"> Logout </a></li>
                        <li>&nbsp</li>
                        <li><a href="#"> Profilo Utente </a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="sidebar">
            <ul>
                <li><a href="#">
                    <span class="title">Dashboard</span></a></li>
                <li><a href="#">
                    <span class="title">Agenda</span>
                </a></li>
                <li><a href="#">
                    <span class="title">Clienti</span>
                </a></li>
                <li><a href="#">
                    <span class="title">Schede</span>
                </a></li>
                <li><a href="#">
                    <span class="title">Pagamenti</span>
                </a></li>
            </ul>
        </div>
    <%}%>
</div>
    <!div>
    <%@include file="footer.jsp"%>
    </div>

    <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script-->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <!--script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script-->

    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>


    <script>
        $(document).ready(function(){
            $(".hamburger").click(function(){
                $(".wrapper").toggleClass("collapse");
            });
        });
    </script>
</body>
</html>