package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountDAO {

    void saveAccount(Account utente) throws IOException;

}
