package it.unisa.c03.myPersonalTrainer.account.service;
import java.io.IOException;

public interface AccountService {
    public boolean loginAccount(String email, String password) throws IOException;
    public boolean checkCredentials(String email, String password) throws IllegalArgumentException;
}