package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountService {

    //metodi che vanno implementati
    public boolean RegisterAccount(Account utente) throws IOException, IllegalArgumentException;

    public boolean checkCredentials(String clientMail, String newPassword)  throws IllegalArgumentException;

    public boolean searchAccountByEmail(String email);

    public void changePassword(String email, String password);
}
