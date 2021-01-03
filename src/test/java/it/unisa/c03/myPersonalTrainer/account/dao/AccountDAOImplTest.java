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

        accountTest = new Account("Account Test", "Account Test","3401212123","cerca@utente.it","PasswordTest1",0);
        DBConnection.getConnection().collection("Account").add(accountTest);
    }


    @AfterAll
    static void clean() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Account").whereEqualTo("email","cerca@utente.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @Test
    void findAccountByEmail() throws InterruptedException, ExecutionException, IOException {

        AccountDAO dao = new AccountDAOImpl();
        Account accountToSearch = dao.findAccountByEmail("cerca@utente.it");

        assertEquals(accountTest.getEmail(), accountToSearch.getEmail());
    }

    @Test
    void updatePassword() throws IOException, ExecutionException, InterruptedException {
        AccountDAO dao = new AccountDAOImpl();
        assertEquals(true, dao.updatePassword(accountTest.getEmail(),"changedPassword56"));
    }


    /* da inserire per vedere se effettivamente ce l'account o meno
    @BeforeAll
    static void searchifexistaccount() throws IOException {

        accountTest = new Account("Account Test", "Account Test","3401212123","cerca@utente.it","PasswordTest1",0);
        DBConnection.getConnection().collection("Account").add(accountTest);
    }
    */


    @AfterAll
    static void afterinsertaccount() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Account").whereEqualTo("email","admin@admin.it").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @Test
    void saveAccount() throws IOException {
        Account utente = new Account("Admin", "Admin", "0000000000", "admin@admin.it", "Adminaccount00", 1);

        AccountDAO adao = new AccountDAOImpl();
        assertTrue(adao.saveAccount(utente));
    }


}
