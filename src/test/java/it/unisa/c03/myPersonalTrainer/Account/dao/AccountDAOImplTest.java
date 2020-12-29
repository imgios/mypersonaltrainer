package it.unisa.c03.myPersonalTrainer.account.dao;

import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {

    @Test
    void saveAccount() throws IOException {
        Account utente = new Account("Admin", "Admin", "0000000000","admin@adminnnnn.it","Adminaccount00",1);

        AccountDAO adao = new AccountDAOImpl();
        assertTrue(adao.saveAccount(utente));
    }





}