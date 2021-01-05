
<!--
<div class="wrapper">
<div class="main_container">
<div class="item">
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>myPersonalTrainer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" -->
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

    <link rel="stylesheet" href="./css/header.css">
    <!-- <link rel="stylesheet" href="./css/headercss.css"> -->

</head>


<body>
<%int validSession=0;%>
<%int role=1;%>
<%if(validSession==1) { %>  <!--if(session.getAttribute("user")!=null) { %>-->
<!--response.sendRedirect("login.jsp");-->
<!--SESSIONE SCADUTA TORNA AL LOGIN-->

<div class="wrapper">
    <div class="top_navbar">
        <div class="hamburgerIn">
            <div class="one"></div>
            <div class="two"></div>
            <div class="three"></div>
        </div>
        <div class="top_menu">
            <div class="logo"><img class="responsive_logo" src="./img/void.png">
            </div>
            <div class="logo"><img class="responsive_logo" src="./img/nlogo.png">
            </div>
            <div>
                <img class="responsive_item_account" src="./img/user1.png">
                <ul>
                    <li><a href="#"> Logout </a></li>
                    <li>&nbsp</li>
                    <li><a href="#"> Profilo Utente </a></li>
                </ul>
            </div>
        </div>
    </div>

    <!--div class="sidebar">
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
    </div-->
<%} else if (role !=1){%>
    <!--HEADER PER CLIENTE-->
<div class="wrapper">
    <div class="top_navbar">
        <div class="hamburger">
            <div class="one"></div>
            <div class="two"></div>
            <div class="three"></div>
        </div>
        <div class="top_menu">
            <div class="logo"><img class="responsive_logo" src="./img/void.png">
            </div>
            <div class="logo"><img class="responsive_logo" src="./img/nlogo.png">
            </div>
            <div>
                <img class="responsive_item_account" src="./img/user1.png">
                <ul>
                    <li><a href="#"> Logout </a></li>
                    <li>&nbsp</li>
                    <li><a href="#"> Profilo Utente </a></li>
                </ul>
            </div>
        </div>
    </div>

    <nav id="sidebar">
        <div id="dismiss">
            <i class="glyphicon glyphicon-arrow-left"></i>
        </div>

        <div class="sidebar-header">
            <h3>myPersonalTrainer</h3>
            <p>Dashboard Cliente</p>
        </div>

        <ul class="list-unstyled components">
            <!-- <p>Dummy Heading</p> -->
            <li class="active">
                <a href="#">Dashboard</a>
            </li>
            <li>
                <a href="#">Statistiche</a>
            </li>
            <li>
                <a href="#">Agenda</a>
            </li>
            <li>
                <a href="#">Cronologia Schede</a>
            </li>
        </ul>
    </nav>

    <%}else if(role==1){%>
    <!--HEADER PER PERSONAL TRAINER-->
    <div class="wrapper">
        <div class="top_navbar">
            <div class="navbar-header">
                <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                    <i class="glyphicon glyphicon-align-left"></i>
                </button>
            </div>
            <div class="top_menu">

                <div class="logo"><img class="responsive_logo" src="./img/void.png">
                </div>
                <div class="logo"><img class="responsive_logo" src="./img/nlogo.png">
                </div>
                <div>
                    <img class="responsive_item_account" src="./img/user1.png">
                    <ul>
                        <li><a href="#"> Logout </a></li>
                        <li>&nbsp</li>
                        <li><a href="#"> Profilo Utente </a></li>
                    </ul>
                </div>
            </div>

        </div>


        <!-- inserimento della barra chiusa di default-->
      <!--
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

        -->
        <nav id="sidebar">
            <div id="dismiss">
                <i class="glyphicon glyphicon-arrow-left"></i>
            </div>

            <div class="sidebar-header">
                <h3>myPersonalTrainer</h3>
                <p>Dashboard Personal Trainer</p>
            </div>

            <ul class="list-unstyled components">
                <!-- <p>Dummy Heading</p> -->
                <li class="active">
                    <a href="#">Dashboard</a>
                </li>
                <li>
                    <a href="#">Statistiche</a>
                </li>
                <li>
                    <a href="#">Agenda</a>
                </li>
                <li>
                    <a href="#">Cronologia Schede</a>
                </li>
            </ul>
        </nav>

    <%}%>
</div>


    <!-- script utili x header -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!--
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
-->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>


    <!-- jQuery CDN -->
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

    <!-- jQuery Custom Scroller CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>




    <!-- script jquery per nascondere la sidebar senza perdere la navbar -->

    <!--
    <script>
      $(document).ready(function(){
        $(".sidebar").hide();
        });
    </script>


    <script>
        function open() {
            $(".sidebar").toggleClass("show");
        }

    </script>

    -->
<!--
    <script>
        $(document).ready(function(){
            $(".hamburger").click(function(){
                $(".sidebar").toggleClass("collapse");
            });
        });
    </script>
-->

    <!-- inserimento script javascript -->
    <script type="text/javascript">
      $(document).ready(function () {
        $("#sidebar").mCustomScrollbar({
          theme: "minimal"
        });

        $('#dismiss, .overlay').on('click', function () {
          $('#sidebar').removeClass('active');
          $('.overlay').fadeOut();
        });

        $('#sidebarCollapse').on('click', function () {
          $('#sidebar').addClass('active');
          $('.overlay').fadeIn();
          $('.collapse.in').toggleClass('in');
          $('a[aria-expanded=true]').attr('aria-expanded', 'false');
        });
      });
    </script>



</body>
</html>
