package it.unisa.c03.myPersonalTrainer.control.service;



public interface AccountService {
    public boolean checkCredentials(String clientMail, String newPassword) throws IllegalArgumentException ;
}
