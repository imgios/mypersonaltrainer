package it.unisa.c03.myPersonalTrainer.account.bean;

import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountBean() {
        String name = "Gino";
        String surname = "Diamante";
        String phone = "1234567895";
        String email = "gino@gmail.com";
        String password = "Palestra14!";
        int role = 0;
        Account account = new Account(name, surname, phone, email, password, role);
        Account account1 = new Account(name, surname, phone, email, password, role);
        assertNotNull(account);
        String stringTest=account.toString();
        assertEquals(name, account.getName());
        assertEquals(surname, account.getSurname());
        assertEquals(phone, account.getPhone());
        assertEquals(email, account.getEmail());
        assertEquals(password, account.getPassword());
        assertEquals(role, account.getRole());
        account1.setName(name);
        account1.setSurname(surname);
        account1.setPhone(phone);
        account1.setEmail(email);
        account1.setPassword(password);
        account1.setRole(role);
        assertEquals(name, account1.getName());
        assertEquals(surname, account1.getSurname());
        assertEquals(phone, account1.getPhone());
        assertEquals(email, account1.getEmail());
        assertEquals(password, account1.getPassword());
        assertEquals(role, account1.getRole());
        assertEquals(stringTest,account.toString());
    }
}
