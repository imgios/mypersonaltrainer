package it.unisa.c03.myPersonalTrainer.control.service;

import it.unisa.c03.myPersonalTrainer.model.bean.Account;

import java.io.IOException;

public interface AccountService {

    //metodi che vanno implementati
    public void RegisterAccount(Account utente) throws IOException;
}
