package it.unisa.c03.myPersonalTrainer.account.service;
        import it.unisa.c03.myPersonalTrainer.account.bean.Account;
        import java.io.IOException;
        import java.util.Collection;
        import java.util.concurrent.ExecutionException;

public interface AccountService {

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
     *
     * @param account Account of user registred into the DataBase.
     * @return Return true if the following account is
     * a Personal Trainer's account.
     * @throws IllegalArgumentException
     */
    boolean verifyIsAdmin(Account account) throws IllegalArgumentException;
}
