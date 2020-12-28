package it.unisa.c03.myPersonalTrainer.account.dao;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import java.io.IOException;

public class AccountDAOImpl implements AccountDAO {
    /**
     * This method allow implements the interface of the AccountDAO.
     * @param email represent the mail of Account.
     * @param password represent the password of Account.
     * @return Account account return the Account of the logged user;
     * @throws IOException
     */
    @Override
    public Account login(String email, String password) throws IOException {
        boolean credentialVerified;
        Account testAccount = new Account();
        AccountServiceImpl account = new AccountServiceImpl();
        credentialVerified = account.checkCredentials(email, password);
        if (credentialVerified) {
            testAccount = account.verifyAccount(email, password);
            return testAccount;
        }
        return null;
    }
}
