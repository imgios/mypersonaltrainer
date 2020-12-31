$(document).ready(function () {
    $('#divError').empty();
    $('#divSuccess').empty();
    $('#divError').hide();
    $('#divSuccess').hide();
});

$(document).ready(function () {
    $("#sendDate").click(function () {
        $('#divError').hide();
        $('#divSuccess').hide();
        var dataSelected = $("#iddataSelected").val();


        $.get('view-availability', {
            "dataSelected": dataSelected,
        }, function (data) {
            // var obj=JSON.parse(data);
            //$('#divError').empty().append("<p> " + data.valueOf() + "</p>");
            // $('#divError').show();

            if (data == 0) {
                $('#divError').empty().append("<p> non ci sono prenotazioni per la data " + dataSelected + "</p>");
                $('#divError').show();

            } else if (data.toString().substr(0, 1) == 1) {
                var str = data.substring(1,);
                $('#divError').empty().append("<p>" + str + "</p>");
                $('#divError').show();
            } else {

                var str = "";
                str += "<h2>Disponibilita' per la data " + dataSelected + "</h2>";
                str += "<table class=" + "table table-hover table-dark" + "> <thead> <tr> <th scope=" + "col" + ">Data</th> <th scope=" + "col" + ">Orario</th>  </tr> </thead> <tbody>";

                $.each(data, function (i, name) {
                    str += "<tr> <td scope=" + "row" + ">"
                        + name.date
                        + "</td>"
                        + "<td scope=" + "row" + ">"
                        + name.time
                        + " </td> </tr>";

                });
                str += "</tbody> </table>"


                $('#divSuccess').empty().append("<p>" + str + "</p>");
                $('#divSuccess').show();
            }
        });
        $('#divError').empty();
        $('#divSuccess').empty();
        $('#divError').hide();
        $('#divSuccess').hide();
    });
});
