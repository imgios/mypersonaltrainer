package it.unisa.c03.myPersonalTrainer.Account.service;



public interface AccountService {
    public boolean checkCredentials(String clientMail, String newPassword) throws IllegalArgumentException ;
}
