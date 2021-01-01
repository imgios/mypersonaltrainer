package it.unisa.c03.myPersonalTrainer.account.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountDAOImplTest {

    static Account accountTest ;
    //AccountDAO dao = new AccountDAOImpl();
    @BeforeAll
    static void setUp() throws IOException {

        accountTest = new Account("clientName", "clientSurname","332","hismail@italy.com","hispassword1",0);
        DBConnection.getConnection().collection("Account").add(accountTest);
    }


    @AfterAll
    static void clean() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Account").whereEqualTo("email","hismail@italy.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @Test
    void findAccountByEmail() throws InterruptedException, ExecutionException, IOException {

        AccountDAO dao = new AccountDAOImpl();
        Account accountToSearch = dao.findAccountByEmail("hismail@italy.com");

        assertEquals(accountTest.getEmail(), accountToSearch.getEmail());
    }

    @Test
    void updatePassword() throws IOException, ExecutionException, InterruptedException {
        AccountDAO dao = new AccountDAOImpl();
        assertEquals(true, dao.updatePassword(accountTest.getEmail(),"changedPassword56"));
    }

    @Test
    void saveAccount() throws IOException {
        Account utente = new Account("Admin", "Admin", "0000000000", "admin@adminnnnn.it", "Adminaccount00", 1);

        AccountDAO adao = new AccountDAOImpl();
        assertTrue(adao.saveAccount(utente));
    }


}
