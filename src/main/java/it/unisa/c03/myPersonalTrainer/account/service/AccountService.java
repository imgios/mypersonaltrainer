package it.unisa.c03.myPersonalTrainer.account.service;


import java.io.IOException;

public interface AccountService {

     boolean checkCredentials(String clientMail, String newPassword)
            throws IllegalArgumentException;

     boolean searchAccountByEmail(String email);

     boolean changePassword(String email, String password) throws IOException;
}
