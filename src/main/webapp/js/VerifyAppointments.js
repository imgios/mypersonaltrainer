$(document).ready(function (){
    $("#appuntamenti").hide();
    $("#giornoscelto").hide();
});




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
                document.getElementById('lista').innerHTML ="<tr><td> nessun appuntamento per il giorno selezionato</td></tr>";
            }
            else {
                for (var i = 0; i < result.length; i++) {
                    s += "<tr>" +
                        "<td>" + result[i].time + "</td>" +
                        "<td>" + result[i].customerMail + "</td>" +
                        "</tr>"
                }
                document.getElementById('lista').innerHTML = s;
                $("#appuntamenti").show();
                $("#giornoscelto").show();
            }
        })


}