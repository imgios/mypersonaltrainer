package it.unisa.c03.myPersonalTrainer.account.service;

        import it.unisa.c03.myPersonalTrainer.account.bean.Account;
        import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

        import java.io.IOException;
        import java.util.concurrent.ExecutionException;


public class AccountServiceImpl implements AccountService {

    /**
     * @exclude
     * */
    private static final int MIN_EMAIL_LENGTH = 7;
    /**
     * @exclude
     * */
    public static final int MAX_EMAIL_LENGTH = 25;
    /**
     * @exclude
     * */
    public static final int MIN_PASSWORD_LENGTH = 1;
    /**
     * @exclude
     * */
    public static final int MAX_PASSWORD_LENGTH = 30;

    /**
     * @exclude
     * */
    private AccountDAO accountDAO;
    /**
     * Service constructor.
     * @param accountDao is required, because is the DAO that
     * all the service methods will call.
     * */
    public AccountServiceImpl(AccountDAO accountDao) {
        accountDAO = accountDao;
    }

    /**
     * check the credential with the regular expression.
     * @param clientMail email of the client
     * @param newPassword new password to update
     * @return clientMail, new Passoword, after check
     * @throws IllegalArgumentException
     */
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
     * This service method checks if an account exists in the database.
     * @param email referring to the account to search for
     * @return true if the account exists, false if not
     */
    @Override
    public boolean searchAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException {

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

    /**
     *
     * @param account Account of user registred into the DataBase.
     * @return Return true if the following account is
     * a Personal Trainer's account.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean verifyIsAdmin(Account account) {
        if (account.getRole() == 1) {
            return true;
        } else {
            return false;
        }
    }

}
