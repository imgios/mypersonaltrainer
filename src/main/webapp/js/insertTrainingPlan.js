function producePrompt(message, promptLocation, color) {
    document.getElementById(promptLocation).innerHTML = message;
    document.getElementById(promptLocation).style.color = color;
}


function validateNameEx() {
    var exercise = document.getElementById("idexercise").value;


    if (!exercise.match("^[a-zA-Z]+$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Input accetta solo lettere per l'esercizio</span>", "controlexercise", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlexercise", "green");
    return true;
}

function validateSeries() {
    var idseries = document.getElementById("idseries").value;


    if (!idseries.match("^[0-9]+$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato non valido, solo numeri</span>", "controlseries", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlseries", "green");
    return true;
}


function validaterepetitions() {
    var idrepetitions = document.getElementById("idrepetitions").value;


    if (!idrepetitions.match("^[0-9]+$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato non valido, solo numeri</span>", "controlrepetitions", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlrepetitions", "green");
    return true;
}


function validaterecoveryTime() {
    var idrecoveryTime = document.getElementById("idrecoveryTime").value;


    if (!idrecoveryTime.match("^[0-9]+$")) {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato non valido, solo numeri</span>", "controlrecoveryTime", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlrecoveryTime", "green");
    return true;
}


function stopsubmit(obj) {
    /* var idrecoveryTime = document.getElementById("idrecoveryTime").value;
     var idrepetitions = document.getElementById("idrepetitions").value;
     var idseries = document.getElementById("idseries").value;
     var idexercise = document.getElementById("idexercise").value;*/


    if (!validateNameEx() || !validaterecoveryTime() || !validaterepetitions() || !validateSeries()) {
        obj.preventDefault();
        return false;
    }


}



