package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean loginAccount(String email, String password) throws IOException {

        boolean result = false;
        Account account = new Account();
        account = accountDAO.verifyAccount(email);

        Account testAccount = new Account(account.getName(), account.getSurname(), account.getPhone(), account.getEmail(), account.getPassword(), account.getRole());
        //the email exists in the DB
        if (account.getEmail() != null) {
            if (email.equals(testAccount.getEmail()) && (password.equals(testAccount.getPassword()))) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }
    @Override
    public boolean checkCredentials(String email, String password)  throws IllegalArgumentException{

        boolean result = false ;

            // check email format
        if(!(email.matches("\\w+([\\._\\-]?\\w+)*@\\w+([\\.\\-]?\\w+)*(\\.\\w+)+$")))
            throw new IllegalArgumentException("Formato email non valido");
            //check password format
        else if(!(password.matches("^[a-zA-Z 0-9 \\@\\._\\!\\?\\-]{8,}$")))
            throw new IllegalArgumentException("Formato password non valido");

        else{
            // all the tests are passed
            result = true ;
        }

        return result ;
    }

    }