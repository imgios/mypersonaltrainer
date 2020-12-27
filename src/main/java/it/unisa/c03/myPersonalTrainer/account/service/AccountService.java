package it.unisa.c03.myPersonalTrainer.account.service;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;

import java.io.IOException;

public interface AccountService {
    boolean loginAccount(String email, String password)
            throws IOException;
    boolean checkCredentials(String email, String password)
            throws IllegalArgumentException;
    Account verifyAccount(String email, String password) throws IOException;
    boolean verifyIsAdmin(Account account) throws IllegalArgumentException;
}