package it.unisa.c03.myPersonalTrainer.Account.dao;

import it.unisa.c03.myPersonalTrainer.Account.bean.Account;

public interface AccountDAO {

    public Account findAccountByEmail(String email) ;

    public void updatePassword(String email , String password) ;

    public String getAccountDocumentIdByEmail(String email);
}
