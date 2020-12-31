package it.unisa.c03.myPersonalTrainer.account.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.Test;




import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    // TC_1.1_1
    @Test
    public void emailLengthNotValid() {
        String mail = "p@l.it" ;
        String password = "password" ;
        String message = "Lunghezza email non valida" ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.1_2
    @Test
    public void emailFormatNotValid()
    {
        String mail = "client@@prova.it" ;
        String password = "password" ;
        String message = "Formato email non valido" ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    // TC_1.1_3
    @Test
    public void finalTest()
    {
        String mail = "client@prova.it" ;
        String password = "password1." ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        assertEquals(true, service.checkCredentials(mail,password));
    }

    @Test
    public void passwordLengthNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "" ;
        String message = "Lunghezza password non valida" ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }

    @Test
    public void passwordFormatNotValid()
    {
        String mail = "client@prova.it" ;
        String password = "prova" ;
        String message = "Formato password non valido" ;

        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        AccountService service  = new AccountServiceImpl(accountDAO);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class , () -> {
            service.checkCredentials(mail,password) ;
        });
        assertEquals(message,exception.getMessage());
    }
    @Test
    public void searchAccountByEmailFalse() throws InterruptedException, ExecutionException, IOException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account();
        a.setEmail(null);
        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);

        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(false, service.searchAccountByEmail("mailnot@italy.it"));

    }

    @Test
    public void searchAccountByEmailTrue() throws InterruptedException, ExecutionException, IOException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account();
        a.setEmail("mail@mail.com");
        Mockito.when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(true, service.searchAccountByEmail("cliente@gmail.com"));
    }

    @Test
    void verifyIsAdminFalse() {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account("clientName", "clientSurname","332","hismail@italy.com","hispassword1",1);
        a.setRole(0);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(false, service.verifyIsAdmin(a));
    }

    @Test
    void verifyIsAdminTrue() {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account("clientName", "clientSurname","332","hismail@italy.com","hispassword1",0);
        a.setRole(1);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(true, service.verifyIsAdmin(a));
    }

    @Test
     void loginAccountTrue() throws IOException, ExecutionException, InterruptedException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account("clientName", "clientSurname","332","hismail@italy.com","hispassword1",0);
        when(accountDAO.findAccountByEmail(anyString())).thenReturn(a);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(true, service.loginAccount("hismail@italy.com", "hispassword1"));
    }

    @Test
    void loginAccountFalse() throws IOException, ExecutionException, InterruptedException {
        AccountDAO accountDAO = Mockito.mock(AccountDAO.class) ;
        Account a = new Account("clientName", "clientSurname","332","hismail@italy.com","hispassword1",0);
        when(accountDAO.findAccountByEmail(anyString())).thenReturn(null);
        AccountService service  = new AccountServiceImpl(accountDAO);
        assertEquals(false, service.loginAccount("hismail@italy.com", "hispasswod1"));
    }
}
