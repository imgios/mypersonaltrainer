package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountService {


     boolean registerAccount(Account utente)
            throws IOException, IllegalArgumentException;

     boolean checkCredentials(String clientMail, String newPassword)
            throws IllegalArgumentException;

     boolean searchAccountByEmail(String email);

     void changePassword(String email, String password);
}
