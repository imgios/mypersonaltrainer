$(document).ready(function (){
    $("#appuntamenti").hide();
    $("#giornoscelto").hide();
    $("#success-alert").hide();
});

function deleteappointmentfromDB(date,time,mail)
{
    $.ajax({
        "type":"POST",
        "url":"./RemoveAppointmentServlet",
        "data":{dataapp:date,ora:time,mail:mail},
        "success":function ()
        {
            seeAppointments();
            $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                $("#success-alert").slideUp(500);
            });
        }

    });
}




function seeAppointments() {
    $("#appuntamenti").show();
    document.getElementById('giornoscelto').innerHTML ="Appuntamenti del:"+document.getElementById('data').value;
    var datacosa = document.getElementById('data').value;
    $.post("AllAppointmentsServlet",{"data":datacosa},
        function (result) {
            var s = '';
            if (result==0)
            {
                $("#appuntamenti").show();
                $("#giornoscelto").show();
                document.getElementById('lista').innerHTML =" nessun appuntamento per il giorno selezionato";
            }
            else {
                for (var i = 0; i < result.length; i++) {
                    var dataappuntamento="\'"+document.getElementById('data').value+"\'";
                    var timeappuntamento="\'"+result[i].time+"\'";
                    var mailappuntamento="\'"+result[i].customerMail+"\'";
                    var param=dataappuntamento+","+timeappuntamento+","+mailappuntamento;
                    var funzione="onclick=\"deleteappointmentfromDB("+param+")\"";
                    s += "<tr>" +
                        "<td>" + result[i].time + "</td>" +
                        "<td>" + result[i].customerMail + "</td>" +
                        "<td><button "+funzione+"><i class=\"far fa-trash-alt\"></i></button></td>"+
                        "</tr>"
                }
                document.getElementById('lista').innerHTML = s;
                $("#appuntamenti").show();
                $("#giornoscelto").show();
            }
        })


}