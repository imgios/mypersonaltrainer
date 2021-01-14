package it.unisa.c03.myPersonalTrainer.account.service;


import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface AccountService {

  /**
   * This function allow to verify the credentials
   * typed in login form.
   * @param email
   * @param password
   * @return true if credentials are in the Database,
   * false if the credentials aren't linked to an account
   * stored in the Database
   * @throws IOException
   * @throws ExecutionException
   * @throws InterruptedException
   */
  boolean loginAccount(String email, String password)
          throws IOException, ExecutionException, InterruptedException;

  /**
   * This function return the list of the accounts registered into the db.
   * @return list of all accounts saved.
   * @throws IOException exception
   */
    ArrayList<Account> viewInfoAccount()
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This function give the possibility to register a
     * new user into the system.
     * @param utente user into the db
     * @return true if is possible to insert the
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
     * @return true if the credentials follow the regular-expression,
     * false when something is typed wrong.
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
     * This method return the account given its mail.
     * @param email referring to the account to search for
     * @return the Account if it exists, null if not
     */
    Account getAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException;

    /**
    * This service method changes the password of an account.
    * @param email of the account
    * @param password updated
    * @return true after the change has taken place
    */
    boolean changePassword(String email, String password)
           throws IOException, ExecutionException, InterruptedException;

    /**
    *
    * @param account Account of user registred into the DataBase.
    * @return Return true if the following account is
    * a Personal Trainer's account.
    * @throws IllegalArgumentException
    */
    boolean verifyIsAdmin(Account account)
            throws IllegalArgumentException;

  /**
   * This method send an Email with new account credentials.
   * @param account
   * @param pw
   * @throws EmailException
   */
   void sendEmail(String account, String pw)
          throws EmailException;

}
