package it.unisa.c03.myPersonalTrainer.account.service;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface AccountService {

     boolean checkCredentials(String clientMail, String newPassword)
            throws IllegalArgumentException;

     /**
      * This service method checks if an account exists in the database.
      * @param email referring to the account to search for
      * @return true if the account exists, false if not
      */
     boolean searchAccountByEmail(String email)
             throws InterruptedException, ExecutionException, IOException;

     /**
      * This service method changes the password of an account.
      * @param email of the account
      * @param password updated
      * @return true after the change has taken place
      */
     boolean changePassword(String email, String password)
             throws IOException, ExecutionException, InterruptedException;
}
