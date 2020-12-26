package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;

public class AccountServiceImpl implements AccountService {

    //metodi implementati

    @Override
    public boolean RegisterAccount(Account utente) throws IOException, IllegalArgumentException {

        AccountDAO accountDAO = new AccountDAOImpl();
        // utente.getEmail();
        //va implementato il check della email che non deve essere già presente nel db
        //System.out.println(utente.getEmail());

        System.out.println("CONTROLLO EMAIL PRIMA DELL'INSERIMENTO NEL DB");


       // System.out.println(utente.getEmail());
       // System.out.println(accountDAO.findAccountByEmail(utente.getEmail()));

       Account ricerca;
       ricerca = accountDAO.findAccountByEmail(utente.getEmail());

       //stampa delle due email
        System.out.println(utente.getEmail());
        System.out.println(ricerca.getEmail());


       //if (utente.getEmail() != ricerca.getEmail()){
        if (ricerca.getEmail() == null){
           System.out.println("email non presente, la inserisco nel DB");
            accountDAO.saveAccount(utente);
            return true;
        } else
       {
          System.out.println("email già presente");
           throw new IllegalArgumentException("email già presente nel DB, utilizza una nuova email");
         //  return false;
          //inserire comando per tornare alla servlet di inserimento
        }

        //accountDAO.saveAccount(utente);
       // System.out.println("STAMPA DAL SERVICE-----");
       // System.out.println("-----");
       // System.out.println("---passaggio al dao--");
    }

    @Override
    public boolean checkCredentials(String clientMail, String newPassword)  throws IllegalArgumentException{


        boolean result = false ;

        // lunghezza email
        if(clientMail.length() < 7 || clientMail.length() > 25)
            throw new IllegalArgumentException("Lunghezza email non valida");

            // formato email
        else if(!(clientMail.matches("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
            throw new IllegalArgumentException("Formato email non valido");

            //controllo lunghezza password
        else if(newPassword.length() < 1 || newPassword.length() > 30)
            throw new IllegalArgumentException("Lunghezza password non valida");

            //controllo formato password
        else if(!(newPassword.matches("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$")))
            throw new IllegalArgumentException("Formato password non valido");

        else{
            // controllo dei test
            result = true ;
        }

        return result ;

    }
}
