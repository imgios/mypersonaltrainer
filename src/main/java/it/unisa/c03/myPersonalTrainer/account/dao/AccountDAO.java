package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public interface AccountDAO {



     Account findAccountByEmail(String email)
             throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method changes the password of an account.
     * @param email of the account
     * @param password updated
     * @return true after the change has taken place
     */
     boolean updatePassword(String email, String password)
             throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method looks for the document id of an account.
     * @param email of the account
     * @return the document Id
     */
     String getAccountDocumentIdByEmail(String email)
             throws IOException, ExecutionException, InterruptedException;

}
