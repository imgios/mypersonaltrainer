$(document).ready(function () {
    $('#divError').hide();
    $('#divSuccess').hide();
});
$(document).ready(function () {

    $("#buttonSubmit").click(function () {
        var idweight = $("#idweight").val();
        var idfatMass = $("#idfatMass").val();
        var idleanMass = $("#idleanMass").val();
        $.get('parameters-controller', {
            "leanMass": idleanMass,
            "fatMass": idfatMass,
            "weight": idweight,
        }, function (data) {
            if (data == 1) {
                $('#divSuccess').empty().append("<p>Inserimento effettuato!</p>");
                $('#divSuccess').show();
            } else {
                $('#divError').empty().append("<p>" + data + "</p>");
                $('#divError').show();
            }
        });
        $('#divError').empty();
        $('#divSuccess').empty();
        $('#divError').hide();
        $('#divSuccess').hide();

    });
});