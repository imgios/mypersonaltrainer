$(document).ready(function(){
$("#datepicker").hide();
$("#ricaricaore").hide();
    $("#cercadisponibilità").hide();
    $("#errorDiv").hide();
    $("#prenota").hide();
    $("#SuccessDiv").hide();
});


function showdataform()
{
    $("#datepicker").show();
    $("#cercadisponibilità").show();
}


function ricaricalista()
{
    $("#listaappuntamento").load(location.href+" #listaappuntamento");
    $("#datepicker").hide();
    $("#ricaricaore").hide();
    $("#cercadisponibilità").hide();
    $("#errorDiv").hide();
    $("#prenota").hide();
    $("#SuccessDiv").hide();
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
    $("#ricaricaore").show();
    var datacosa = document.getElementById('data').value;
    $.ajax({
        "type": "POST",
        "url": "./HoursServlet",
        "data": {dataappuntamento: datacosa},
        "success": function (result) {

            if (result == 0) {
                document.getElementById('listaore').innerHTML = "nessuna ora disponibile per questa data";
                $("#prenota").hide();
            } else {

                var s = "";
                for (var i = 0; i < result.length; i++) {
                    s += '<a class="list-group-item list-group-item-action" id=' + result[i].time + ' aria-controls=' + result[i].time + ' role="tab" data-toggle="list" onclick="savetime(this)">' + result[i].time + '</a>';
                }
                document.getElementById('listaore').innerHTML = s;
                $("#prenota").show();
                $("#ricaricaore").load();
            }
        }
    })
}
    function prenota(mailutente){
        var data=$("#data").val();
        var time=$("#time").val();
        $.post('RequestAppointmentServlet',{
            "data": data,
            "time": time,
            "mailutente": mailutente
        },function (result){
            if (result){
                ricaricalista();
                $("#SuccessDiv").empty().append("<p>Appuntamento prenotato!</p>");
                $("#SuccessDiv").show();

            }
            else{
                $("#errorDiv").empty().append("<p>Errore durante la prenotazione!</p>");
                $("#errorDiv").show();
            }
        });
        $("#errorDiv").empty();
        $("#SuccessDiv").empty();
        $("#errorDiv").hide();
        $("#SuccessDiv").hide();
    }
