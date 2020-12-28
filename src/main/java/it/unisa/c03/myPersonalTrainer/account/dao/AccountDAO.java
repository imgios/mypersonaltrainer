package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public interface AccountDAO {


    //funzione per controllare dal db se l'utente è già
    // registrato con quella email
     Account findAccountByEmail(String email) throws IOException, ExecutionException, InterruptedException;

     boolean updatePassword(String email, String password) throws IOException, ExecutionException, InterruptedException;

     String getAccountDocumentIdByEmail(String email) throws IOException, ExecutionException, InterruptedException;

}
