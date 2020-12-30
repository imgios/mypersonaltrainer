function ricaricalista()
{
    $('#listaappuntamento').load(window.location.href+'#listaappuntamento');
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