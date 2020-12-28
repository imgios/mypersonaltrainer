package it.unisa.c03.myPersonalTrainer.account.dao;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import java.io.IOException;

public interface AccountDAO {
    /**
     * This interface allow the User to logged in his personal
     * dashboard and services.
     * @param mail represent the mail of Account.
     * @param password represent the password of Account.
     * @return Account account return the Account of the logged user.
     * @throws IOException
     */
    Account login(String mail, String password) throws IOException;
}
