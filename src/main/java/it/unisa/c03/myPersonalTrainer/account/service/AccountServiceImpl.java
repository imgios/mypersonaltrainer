package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;

public class AccountServiceImpl implements AccountService {

    private static final int MIN_EMAIL_LENGTH = 7;
    public static final int MAX_EMAIL_LENGTH = 25;
    public static final int MIN_PASSWORD_LENGTH = 1;
    public static final int MAX_PASSWORD_LENGTH = 30;

    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean registerAccount(final Account utente)
            throws IOException, IllegalArgumentException {

        AccountDAO accountDAO = new AccountDAOImpl();
        // utente.getEmail();
        //va implementato il check della email che non deve
        // essere già presente nel db
        //System.out.println(utente.getEmail());

        System.out.println("CONTROLLO EMAIL PRIMA DELL'INSERIMENTO NEL DB");


       // System.out.println(utente.getEmail());
       // System.out.println(accountDAO.findAccountByEmail(utente.getEmail()));

       Account ricerca;
       ricerca = accountDAO.findAccountByEmail(utente.getEmail());

       //stampa delle due email
        System.out.println(utente.getEmail());
        System.out.println(ricerca.getEmail());


       //if (utente.getEmail() != ricerca.getEmail()) {
        if (ricerca.getEmail() == null) {
           System.out.println("email non presente, la inserisco nel DB");
            accountDAO.saveAccount(utente);
            return true;
        } else {
          System.out.println("email già presente");
           throw new IllegalArgumentException("email già presente nel DB, "
                   + "utilizza una nuova email");
         //  return false;
          //inserire comando per tornare alla servlet di inserimento
        }

        //accountDAO.saveAccount(utente);
       // System.out.println("STAMPA DAL SERVICE-----");
       // System.out.println("-----");
       // System.out.println("---passaggio al dao--");
    }

    @Override
    public boolean checkCredentials(final String clientMail,
                                    final String newPassword)
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

    @Override
    public boolean searchAccountByEmail(final String email) {

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
    public void changePassword(final String email,
                               final String password) {

        accountDAO.updatePassword(email, password);
    }
}