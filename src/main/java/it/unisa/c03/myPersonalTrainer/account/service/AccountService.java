package it.unisa.c03.myPersonalTrainer.account.service;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface AccountService {

     boolean checkCredentials(String clientMail, String newPassword)
            throws IllegalArgumentException;

     boolean searchAccountByEmail(String email) throws InterruptedException, ExecutionException, IOException;

     boolean changePassword(String email, String password) throws IOException, ExecutionException, InterruptedException;
}
