package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;
import java.util.Collection;

public class AccountServiceImpl implements AccountService {

    /*
    * This class calls back AccountDAOImpl
    * */

    @Override
    public Collection<Account> viewInfoAccount() throws IOException {

        AccountDAO p = new AccountDAOImpl();
        return p.getAccounts();

    }

}