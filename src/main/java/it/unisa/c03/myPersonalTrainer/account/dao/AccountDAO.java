package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountDAO {

    //funzione per salvare un nuovo utente in firestore
    void saveAccount(Account utente) throws IOException;

    //funzione per controllare dal db se l'utente è già registrato con quella email
    public Account findAccountByEmail(String email);

}
