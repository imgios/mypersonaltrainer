package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.util.ArrayList;
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

    private static final int PORT = 465;

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

    public AccountServiceImpl() {
    }

    /**
    * Check the credential before login.
    * @param email
    * @param password
    * @return
    * @throws IOException
    * @throws ExecutionException
    * @throws InterruptedException
    */

    @Override
    public boolean loginAccount(String email, String password)
          throws IOException, ExecutionException, InterruptedException {
      Account accountLogged;
      accountLogged = accountDAO.findAccountByEmail(email);
        return accountLogged != null;
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
     * This method return the account given its mail.
     * @param email referring to the account to search for
     * @return the Account if it exists, null if not
     */
    @Override
    public Account getAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException {
        Account account = new Account();
        account = accountDAO.findAccountByEmail(email);

        //the email exists in the DB
        if (account.getEmail() != null) {
            return account;
        }
        //the email doesn't exist in the DB
        return null;
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
    public ArrayList<Account> viewInfoAccount()
            throws IOException, ExecutionException, InterruptedException {
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

        //System.out.println("CONTROLLO EMAIL PRIMA DELL'INSERIMENTO NEL DB");

        // System.out.println(utente.getEmail());
        // System.out.println(accountDAO.findAccountByEmail(utente.getEmail()));

        Account ricerca;
        ricerca = accountDAO.findAccountByEmail(utente.getEmail());

        //stampa delle due email
        //System.out.println(utente.getEmail());
        //System.out.println(ricerca.getEmail());

        //if (utente.getEmail() != ricerca.getEmail()){
        if (ricerca.getEmail() == null) {
          //  System.out.println("email non presente, la inserisco nel DB");
            accountDAO.saveAccount(utente);
            return true;
        } else {
            //System.out.println("email già presente");
            throw new IllegalArgumentException("email già presente"
                    + " nel DB, utilizza una nuova email");
        }
    }

    /**
     * @param account Account of registred user into the DataBase.
     * @return Return true if the following account is
     * a Personal Trainer's account.
     * @throws IllegalArgumentException
     */
    @Override
    public boolean verifyIsAdmin(Account account) {
        return account.getRole() == 1;
    }

    /**
     * This method send an Email with new account credentials.
     * @param account
     * @param pw
     * @throws EmailException
     */
    public void sendEmail(String account, String pw)
            throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(PORT);
        email.setAuthenticator(new DefaultAuthenticator
                ("mypt.gps.is@gmail.com", "mypt2021"));
        email.setSSLOnConnect(true);
        email.setFrom("mypt.gps.is@gmail.com");
        email.setSubject("Registrazione myPersonalTrainer");
        email.setMsg("Benvenuto/a in myPersonalTrainer. "
                + "La sua password per accedere al sistema è: "
                + pw);
        email.addTo(account);
        email.send();
    }

}
