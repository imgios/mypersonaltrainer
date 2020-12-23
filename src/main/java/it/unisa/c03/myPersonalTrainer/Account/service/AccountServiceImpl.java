package it.unisa.c03.myPersonalTrainer.Account.service;

import it.unisa.c03.myPersonalTrainer.Account.service.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public boolean checkCredentials(String clientMail, String newPassword)  throws IllegalArgumentException{


        boolean result = false ;

        if(clientMail.length() < 7 || clientMail.length() > 25)
            throw new IllegalArgumentException("Lunghezza email non valida");

        else if(!(clientMail.matches("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
            throw new IllegalArgumentException("Formato email non valido");

        else if(newPassword.length() < 1 || newPassword.length() > 30)
            throw new IllegalArgumentException("Lunghezza password non valida");

        else if(!(newPassword.matches("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$")))
            throw new IllegalArgumentException("Formato password non valido");

        else{
                result = true ;
            System.out.println("Tutto ok: + " + result);
        }

        return result ;

    }
}
