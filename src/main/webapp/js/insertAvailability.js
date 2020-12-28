$(document).ready(function () {
    $('#divError').hide();
    $('#divSuccess').hide();
});
function validName(){
    var timeSelected = $("#idtimeSelected").val();
    if(timeSelected < 9 || timeSelected > 19) return false;
    return true;
}
$(document).ready(function () {
    $("#buttonSubmit").click(function () {
        $('#divError').hide();
        $('#divSuccess').hide();
        var dataSelected = $("#iddataSelected").val();
        var timeSelected = $("#idtimeSelected").val();


        if(validName()==false){
            $('#divError').empty().append("<p>Orario non valido puoi scegliere tra 9 e 19</p>");
            $('#divError').show();
            return;
        }
        $.get('availability-controller', {
            "dataSelected": dataSelected,
            "timeSelected": timeSelected,
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
