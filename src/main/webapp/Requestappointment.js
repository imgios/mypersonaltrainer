$(document).ready(function(){
    $("#datepicker").hide();
    $("#cercadisponibilità").hide();
    $("#listatempo").hide();

});

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