package it.unisa.c03.myPersonalTrainer.account.service;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountService {
    /**
     *
     * @param email email of user registred into the DataBase.
     * @param password password of user registred into the DataBase.
     * @return Return true if credential from the Login form
     * matches with the credential into the DataBase.
     * @throws IOException
     */
    boolean loginAccount(String email, String password)
            throws IOException;
    /**
     *
     * @param email email of user registred into the DataBase.
     * @param password password of user registred into the DataBase.
     * @return Return true if the credential from Login form are
     * correct for the regular-expression.
     * @throws IllegalArgumentException
     */
    boolean checkCredentials(String email, String password)
            throws IllegalArgumentException;
    /**
     *
     * @param email email of user registred into the DataBase.
     * @param password
     * @return Return the Account that matches with the
     * email and password from Login Form.
     * @throws IOException
     */
    Account verifyAccount(String email, String password) throws IOException;
    /**
     *
     * @param account Account of user registred into the DataBase.
     * @return Return true if the following account is
     * a Personal Trainer's account.
     * @throws IllegalArgumentException
     */
    boolean verifyIsAdmin(Account account) throws IllegalArgumentException;
}
