function ricaricalista()
{
    $('#listaappuntamento').reload();
}
function deleteappointmentfromDB(date,time,mail)
{
    $.ajax({
        type:"GET",
        url:"./RemoveAppointmentServlet",
        data:{dataapp:date,ora:time,mail:mail},
        async: false,
        cache: false,
        success:function ()
        {
            ricaricalista();
        }

    })
}