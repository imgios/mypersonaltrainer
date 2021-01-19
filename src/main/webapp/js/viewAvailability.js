$(document).ready(function () {
    $('#divError').empty();
    $('#divError').hide();
    $('#divSuccess').empty();
    $('#divSuccess').hide();
    $('#divList').hide();
    $('#divList').empty();
});

function mostra() {
    var dataSelected = $("#iddataSelected").val();

    $('#divError').empty().append("<p>" + dataSelected + "</p>");
    $('#divError').show();
}

$(document).ready(function () {
    $("#sendDate").click(function () {
        $('#divError').hide();
        $('#divSuccess').hide();
        var dataSelected = $("#iddataSelected").val();


        $.get('view-availability', {
            "dataSelected": dataSelected,
        }, function (data) {

            if (data == 0) {
                $('#divError').empty().append("<p> Non ci sono disponibilita' fissate per la data " + dataSelected + "</p>");
                $('#divError').show();
                $("p").css({"font-style": "oblique", " font-variant": "small-caps"});


            } else if (data.toString().substr(0, 1) == 1) {
                var str = data.substring(1,);
                $('#divError').empty().append("<p>" + str + "</p>");
                $('#divError').show();
                $("p").css({"font-weight": "bold", " font-style": "italic"});
            } else {

                var str = "";
                str += "<h2>Disponibilita' per la data " + dataSelected + "</h2>";
                str += "<table class='table table-hover'> <thead> <tr> <th>Data</th> <th>Orario</th>  </tr> </thead> <tbody>";

                $.each(data, function (i, name) {
                    str += "<tr class='table-primary'> <td>"
                        + name.date
                        + "</td>"
                        + "<td>"
                        + name.time
                        + " </td> </tr>";

                });
                str += "</tbody> </table>"


                $('#divList').empty().append("<p>" + str + "</p>");
                $('#divList').show();
            }
        });
        $('#divList').empty();
        $('#divList').hide();
        $('#divSuccess').empty();
        $('#divSuccess').hide();
        $('#divError').hide();
        $('#divError').empty();
    });
});
