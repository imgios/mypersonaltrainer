package it.unisa.c03.myPersonalTrainer.account.dao;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface AccountDAO {

    /**
     * this function save the account into the db.
     * @param utente the user
     * @throws IOException
     * @return true or false
     */
    boolean saveAccount(Account utente) throws IOException;

    /** this function can search if the email is
     * just registered into the db.
     * @param email is the pk to find the user into the db
     * @return the Account if the email is registered,
     * or "null" if the email isn't registered.
     */
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

    /**
     * This function read the saved accounts into the db.
     * @return List of the accounts
     * @throws IOException
     */
    ArrayList<Account> getAccounts()
            throws IOException, ExecutionException,
            InterruptedException;
}
