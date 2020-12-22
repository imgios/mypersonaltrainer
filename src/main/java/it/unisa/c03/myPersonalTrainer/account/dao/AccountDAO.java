package it.unisa.c03.myPersonalTrainer.model.dao;

import it.unisa.c03.myPersonalTrainer.model.bean.Account;

import java.io.IOException;

public interface AccountDAO {

    void viewAccountInfo(Account user) throws IOException;

}
