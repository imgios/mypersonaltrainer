package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountService {

    //metodi che vanno implementati
    public void RegisterAccount(Account utente) throws IOException;
}
