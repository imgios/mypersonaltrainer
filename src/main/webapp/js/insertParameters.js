$(document).ready(function () {
    $('#divError').hide();
    $('#divSuccess').hide();
});

function validateWeight() {
    var weight = document.getElementById("idweight").value;

    if (!weight.match("([0-9]){2,3}\\.?[0-9]{0,2}?$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato peso non valido!</span>", "controlweight", "red");
        return false;
    }

    if (weight < 40 || weight > 150) {
        producePrompt("<span class='badge badge-pill badge-danger'>Lunghezza peso non valido: compreso 40  tra e 150!</span>", "controlweight", "red");
        return false;
    }
    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlweight", "green");

    return true;
}

function validatefatMass() {
    var fatMass = document.getElementById("idfatMass").value;


    if (!fatMass.match("([0-9]+\\%){1,2}$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato massa grassa non valida</span>", "controlfatMass", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlfatMass", "green");

    return true;
}

function validateLeanMass() {
    var leanMass = document.getElementById("idleanMass").value;

    if (!leanMass.match("([0-9]+\\%){1,2}$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato massa magra non valida</span>", "controlleanMass", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlleanMass", "green");

    return true;
}

function producePrompt(message, promptLocation, color) {
    document.getElementById(promptLocation).innerHTML = message;
    document.getElementById(promptLocation).style.color = color;
}

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