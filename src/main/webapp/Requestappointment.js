$(document).ready(function(){

});



function ricaricatempo()
{
    $("#ricaricaore").load("#listatempo");
}


function showdataform()
{
    $("#datepicker").show();
    $("#cercadisponibilità").show();
}

function showtime() {
    $("#listatempo").show();

}

function ricaricalista()
{
    $("#ricarica").load("#ricarica");
}


function deleteappointmentfromDB(date,time,mail)
{
    $.ajax({
        "type":"POST",
        "url":"./RemoveAppointmentServlet",
        "data":{dataapp:date,ora:time,mail:mail},
        "success":function ()
        {
            ricaricalista();
        }

    });
}

function caricaore() {
    var datacosa=document.getElementById('data').value;
    $.ajax({
        "type":"POST",
        "url":"RequestAppointment.jsp",
        "data":{dataappuntamento:datacosa.toString()},
        "success":function ()
        {

        }
    })

}