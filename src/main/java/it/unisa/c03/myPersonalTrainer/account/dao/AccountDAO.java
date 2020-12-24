package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.Collection;

public interface AccountDAO {

    public Collection<Account> getAccounts() throws IOException;

}