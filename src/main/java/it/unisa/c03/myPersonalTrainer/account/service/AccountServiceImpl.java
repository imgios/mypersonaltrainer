package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class AccountServiceImpl implements AccountService {

    private static final int MIN_EMAIL_LENGTH = 7;
    public static final int MAX_EMAIL_LENGTH = 25;
    public static final int MIN_PASSWORD_LENGTH = 1;
    public static final int MAX_PASSWORD_LENGTH = 30;

    private AccountDAO accountDAO ;

    public AccountServiceImpl(AccountDAO accountDao)
    {
        accountDAO = accountDao ;
    }

    @Override
    public boolean checkCredentials(String clientMail,
                                    String newPassword)
            throws IllegalArgumentException {


        boolean result = false;

        // lunghezza email
        if (clientMail.length() < MIN_EMAIL_LENGTH
                || clientMail.length() > MAX_EMAIL_LENGTH) {
            throw new IllegalArgumentException("Lunghezza email non valida");
        } else if (!(clientMail.matches(
                "\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$"))) {
            // formato email
            throw new IllegalArgumentException("Formato email non valido");
        } else if (newPassword.length() < MIN_PASSWORD_LENGTH
                || newPassword.length() > MAX_PASSWORD_LENGTH) {
            //controllo lunghezza password
            throw new IllegalArgumentException("Lunghezza password non valida");
        } else if (!(newPassword.matches(
                "^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$"))) {
            //controllo formato password
            throw new IllegalArgumentException("Formato password non valido");
        } else {
            // controllo dei test
            result = true;
        }

        return result;

    }

    /**
     * ajasjcjas.
     * @param email to search
     * @return true or false perche
     */
    @Override
    public boolean searchAccountByEmail(String email) throws InterruptedException, ExecutionException, IOException {

        boolean result = false;
        Account account = new Account();
        account = accountDAO.findAccountByEmail(email);

        //the email exists in the DB
        if (account.getEmail() != null) {
            result = true;
        } else if (account.getEmail() == null) {
            //the email doesn't exist in the DB
            result = false;
        }

        return result;
    }

    @Override
    // java doc
    public boolean changePassword(String email,
                               String password) throws IOException, ExecutionException, InterruptedException {

        return accountDAO.updatePassword(email, password);
    }
}
