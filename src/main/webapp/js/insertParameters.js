$(document).ready(function () {
    $('#divError').hide();
    $('#divSuccess').hide();
});

function validateWeight() {
    var weight = $("#idweight").val();

    if (weight == undefined) {
        $('#divError').empty().append("<p>Inserisci peso</p>");
        $('#divError').show();
        return false;
    }

    if (!weight.match("([0-9]){2,3}\\.?[0-9]{0,2}?$")) {
        $('#divError').empty().append("<p>Formato peso non valido</p>");
        $('#divError').show();
        return false;
    }


    if (weight < 40 || weight > 150) {
        $('#divError').empty().append("<p>Lunghezza peso non valida</p>");
        $('#divError').show();
        return false;
    }
}

function validatefatMass() {
    var fatMass = $("#idfatMass").val();
    if (fatMass == undefined) {
        $('#divError').empty().append("<p>Inserisci massa grassa </p>");
        $('#divError').show();
        return false;
    }

    if (!fatMass.match("([0-9]+\\%){1,2}$")) {
        $('#divError').empty().append("<p>Formato massa grassa non valida</p>");
        $('#divError').show();
        return false;
    }
}


function validateLeanMass() {
    var leanMass = $("#idleanMass").val();

    if (leanMass == undefined) {
        $('#divError').empty().append("<p>Inserisci massa magra </p>");
        $('#divError').show();
        return false;
    }

    if (!leanMass.match("([0-9]+\\%){1,2}$")) {
        $('#divError').empty().append("<p>Formato massa magra non valida</p>");
        $('#divError').show();
        return false;
    }
}


$(document).ready(function () {

    $("#buttonSubmit").click(function () {
        var idweight = $("#idweight").val();
        var idfatMass = $("#idfatMass").val();
        var idleanMass = $("#idleanMass").val();


        if (validateWeight() == false) {
            return;
        }

        if (validatefatMass() == false) {
            return;
        }
        if (validateLeanMass() == false) {
            return;
        }


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