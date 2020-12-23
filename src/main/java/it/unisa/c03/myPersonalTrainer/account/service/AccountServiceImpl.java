package it.unisa.c03.myPersonalTrainer.account.service;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;

import java.io.IOException;
import java.util.HashMap;

public class AccountServiceImpl implements AccountService {

    @Override
    public HashMap<String,String> viewInfoAccount(String email) throws IOException {

        AccountDAO p = new AccountDAOImpl();

        Account a = p.findAccountByEmail(email);

        HashMap<String, String> dati = new HashMap<String, String>();

        dati.put("name",a.getName());
        dati.put("surname", a.getSurname());
        dati.put("email", a.getEmail());
        dati.put("phone", a.getPhone());

        return dati;

    }

}