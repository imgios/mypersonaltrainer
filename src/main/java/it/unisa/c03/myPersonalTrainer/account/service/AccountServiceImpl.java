package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;

import java.io.IOException;
import java.util.Collection;
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
     * This service method changes the password of an account.
     * @param email of the account
     * @param password updated
     * @return true after the change has taken place
     */
    @Override
    public boolean changePassword(String email, String password)
            throws IOException, ExecutionException, InterruptedException {

        return accountDAO.updatePassword(email, password);
    }


    /**
     * This function return the list of the account insert into the db.
     * @return list of every account store
     * @throws IOException exception
     */
    @Override
    public Collection<Account> viewInfoAccount() throws IOException {
        AccountDAO p = new AccountDAOImpl();
        return p.getAccounts();
    }

    /**
     * this function register the account.
     * @param utente user into the db.
     * @return utente user mem into the db.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Override
    public boolean registerAccount(Account utente)
            throws IOException, IllegalArgumentException,
            ExecutionException, InterruptedException {

        AccountDAO accountDAO = new AccountDAOImpl();

        System.out.println("CONTROLLO EMAIL PRIMA DELL'INSERIMENTO NEL DB");

        // System.out.println(utente.getEmail());
        // System.out.println(accountDAO.findAccountByEmail(utente.getEmail()));

        Account ricerca;
        ricerca = accountDAO.findAccountByEmail(utente.getEmail());

        //stampa delle due email
        System.out.println(utente.getEmail());
        System.out.println(ricerca.getEmail());

        //if (utente.getEmail() != ricerca.getEmail()){
        if (ricerca.getEmail() == null) {
            System.out.println("email non presente, la inserisco nel DB");
            accountDAO.saveAccount(utente);
            return true;
        } else {
            System.out.println("email già presente");
            throw new IllegalArgumentException("email già presente"
                    + " nel DB, utilizza una nuova email");
        }

    }

}
