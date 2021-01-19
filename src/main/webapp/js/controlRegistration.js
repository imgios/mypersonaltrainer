function validateName()
{
    var name = document.getElementById("name").value;

    if(name.length == 0)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Il campo non pu&ograve rimanere vuoto!</span>", "controlName" , "red");
        return false;
    }

    if(name.length <= 2)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Nome troppo corto!</span>", "controlName" , "red");
        return false;
    }
    if(!name.match(/^(?=.{1,50}$)[a-z]+(?:['_.\s][a-z]+)*$/i))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Input accetta solo lettere</span>", "controlName", "red" );
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlName" , "green");
    return true;
}



function validatePhone()
{
    var phone = document.getElementById("phone").value;

    if(phone.length == 0)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Il campo non pu&ograve rimanere vuoto!</span>", "controlPhone" , "red");
        return false;
    }

    if(!phone.match(/^[0-9]{10}$/))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Includere solo cifre!(10)</span>", "controlPhone", "red" );
        return false;
    }
    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlPhone" , "green");
    return true;
}

function validateEmail()
{
    var email = document.getElementById("email").value;

    if(email.length == 0)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Il campo non pu&ograve rimanere vuoto!</span>", "controlEmail", "red");
        return false;
    }

    if(!(email.match("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Formato Email non valido!</span>","controlEmail", "red");
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlEmail", "green");

}

function validateSurname()
{
    var surname = document.getElementById("surname").value;

    if(surname.length == 0)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Il campo non pu&ograve rimanere vuoto!</span>", "controlSurname" , "red");
        return false;
    }

    if(surname.length <= 2)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Troppo corto!</span>", "controlSurname" , "red");
        return false;
    }
    if(!surname.match(/^(?=.{1,50}$)[a-z]+(?:['_.\s][a-z]+)*$/i))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Qui puoi inserire solo lettere!</span>", "controlSurname", "red" );
        return false;
    }

    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlSurname" , "green");
    return true;
}


function validatePassword()
{
    var password = document.getElementById("password").value;
    if(password.length == 0)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Il campo non pu&ograve rimanere vuoto!</span>", "controlPassword", "red");
        return false;
    }

    if(!(password.match("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$")))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Password non sicura </span>", "controlPassword", "red");
        return false;
    }
    producePrompt("<span class='badge badge-pill badge-success'><svg class='bi bi-check-circle' width='1em' height='1em' viewBox='0 0 16 16' fill='currentColor' xmlns='http://www.w3.org/2000/svg'><path fill-rule='evenodd' d='M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z'/><path fill-rule='evenodd' d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z'/></svg></span>", "controlPassword","green");
    return true;
}


function stopsubmit()
{
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if(name.length <= 2)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>Nome troppo corto!</span>", "errorMessage" , "red");
        event.preventDefault();
        return false;
    }

    if(!name.match(/^(?=.{1,50}$)[a-z]+(?:['_.\s][a-z]+)*$/i))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - caratteri invalidi NOME!</span>", "errorMessage", "red" );
        event.preventDefault();
        return false;
    }
    if(surname.length <= 2)
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - Cognome troppo corto!</span>", "errorMessage" , "red");
        event.preventDefault();
        return false;
    }

    if(!surname.match(/^(?=.{1,50}$)[a-z]+(?:['_.\s][a-z]+)*$/i))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - caratteri invalidi COGNOME!</span>", "errorMessage", "red" );
        event.preventDefault();
        return false;
    }

    if(!phone.match(/^[0-9]{10}$/))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - caratteri invalidi numero di telefono!</span>", "errorMessage", "red" );
        event.preventDefault();
        return false;
    }
    if(!email.match(/^[A-Za-z\._\-0-9]*[@][A-Za-z]*[\.][a-z]{2,4}$/))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - caratteri invalidi Email!</span></span>","errorMessage", "red");
        event.preventDefault();
        return false;
    }
    if(!password.match(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/))
    {
        producePrompt("<span class='badge badge-pill badge-danger'>ATTENZIONE - caratteri invalidi Password!</span>", "errorMessage", "red");
        event.preventDefault();
        return false;
    }

}

function producePrompt(message,promptLocation,color)
{
    document.getElementById(promptLocation).innerHTML = message;
    document.getElementById(promptLocation).style.color = color;
}