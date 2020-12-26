package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;
import java.util.Collection;

public interface AccountService {

    public Collection<Account> viewInfoAccount() throws IOException;

}