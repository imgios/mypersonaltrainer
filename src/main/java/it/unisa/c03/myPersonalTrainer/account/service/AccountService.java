package it.unisa.c03.myPersonalTrainer.control.service;

import it.unisa.c03.myPersonalTrainer.model.bean.Account;

import java.io.IOException;

public interface AccountService {

    public  void viewAccountInfo(Account user) throws IOException;

}
