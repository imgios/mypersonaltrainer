$(document).ready(function() {
    $('#testoProfilo').hide();
    $('#testoAbbonamento').hide();
    $('#testoPassword').show();
});



function showProfile(){
    $('#testoPassword').hide();
    $('#testoAbbonamento').hide();
    $('#testoProfilo').show();
}

function showPassword() {
    $('#testoProfilo').hide();
    $('#testoAbbonamento').hide();
    $('#testoPassword').show();
}

function showAbbonamento() {
    $('#testoProfilo').hide();
    $('#testoPassword').hide();
    $('#testoAbbonamento').show();
}