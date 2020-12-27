package it.unisa.c03.myPersonalTrainer.account.service;



public interface AccountService {

     boolean checkCredentials(String clientMail, String newPassword)
            throws IllegalArgumentException;

     boolean searchAccountByEmail(String email);

     void changePassword(String email, String password);
}
