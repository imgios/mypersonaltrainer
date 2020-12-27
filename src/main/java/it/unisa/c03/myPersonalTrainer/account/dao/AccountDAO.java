package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;


public interface AccountDAO {


    //funzione per controllare dal db se l'utente è già
    // registrato con quella email
     Account findAccountByEmail(String email);

     boolean updatePassword(String email, String password) throws IOException;

     String getAccountDocumentIdByEmail(String email);

}
