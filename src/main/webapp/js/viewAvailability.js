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
            if (data == 0) {
                $('#divError').empty().append("<p>Non ci sono disponibilita' per il giorno " + dataSelected + "</p>");
                $('#divError').show();

            }

            if (data.charAt(0) == 1) {
                var str = data.substring(1,);
                $('#divError').empty().append("<p>"+str+"</p>");
                $('#divError').show();
            } else {

                var str = "";
                str += "<table class=" + "table table-hover table-dark" + "> <thead> <tr> <th scope=" + "col" + ">Data</th> <th scope=" + "col" + ">Orario</th>  </tr> </thead> <tbody>";

                $.each(obj, function (i, name) {
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
