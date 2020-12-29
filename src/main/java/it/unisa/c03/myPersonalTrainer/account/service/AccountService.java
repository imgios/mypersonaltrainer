package it.unisa.c03.myPersonalTrainer.account.service;


import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface AccountService {


  /**
   * This function return the list of the account insert into the db.
   * @return list of every account store
   * @throws IOException exception
   */
    Collection<Account> viewInfoAccount() throws IOException;

    /**
     * This function give the possibility to register a
     * new user into the system.
     * @param utente user into the db
     * @return true if is possibly to insert the
     * account after check of the credentials,
     * false when fails the check of the
     * credentials or the email is just registered
     * @throws IOException //
     * @throws IllegalArgumentException //
     */
     boolean registerAccount(Account utente)
             throws IOException, IllegalArgumentException,
             ExecutionException, InterruptedException;

    /**
     * This function check the credentials of
     * the client before insert into the db.
     * @param clientMail email of the client
     * @param newPassword new password to update
     * @return true if the credentials follow the expression-regular,
     * false when something is typing wrong.
     * @throws IllegalArgumentException //
     */
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
