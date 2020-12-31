package it.unisa.c03.myPersonalTrainer.account.dao;

        import it.unisa.c03.myPersonalTrainer.account.bean.Account;
        import java.io.IOException;
        import java.util.concurrent.ExecutionException;

public interface AccountDAO {

    /** this function can search if the email is
     * just registered into the db.
     * @param email is the pk to find the user into the db
     * @return if the email is in the parameters of
     * the user and if the email isn't registered "null"
     */
    Account findAccountByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;
}
