package it.unisa.c03.myPersonalTrainer.Account.service;

import it.unisa.c03.myPersonalTrainer.Account.service.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public boolean checkCredentials(String clientMail, String newPassword)  throws IllegalArgumentException{


        boolean result = false ;

        // check email length
        if(clientMail.length() < 7 || clientMail.length() > 25)
            throw new IllegalArgumentException("Lunghezza email non valida");

        // check email format
        else if(!(clientMail.matches("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
            throw new IllegalArgumentException("Formato email non valido");

        //check password length
        else if(newPassword.length() < 1 || newPassword.length() > 30)
            throw new IllegalArgumentException("Lunghezza password non valida");

        //check password format
        else if(!(newPassword.matches("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$")))
            throw new IllegalArgumentException("Formato password non valido");

        else{
            // all the tests are passed
                result = true ;
        }

        return result ;

    }
}
