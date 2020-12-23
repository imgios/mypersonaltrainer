package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;

public class AccountServiceImpl implements AccountService {

    //metodi implementati

    @Override
    public void RegisterAccount(Account utente) throws IOException {

        AccountDAO accountDAO = new AccountDAOImpl();
        // utente.getEmail();
        //va implementato il check della email che non deve essere già presente nel db
        //System.out.println(utente.getEmail());

        accountDAO.saveAccount(utente);
        System.out.println("STAMPA DAL SERVICE-----");
        System.out.println("-----");
        System.out.println("---passaggio al dao--");
    }
}
