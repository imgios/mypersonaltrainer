package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;



public interface AccountDAO {


    //funzione per controllare dal db se l'utente è già
    // registrato con quella email
     Account findAccountByEmail(String email);

     void updatePassword(String email, String password);

     String getAccountDocumentIdByEmail(String email);

}
