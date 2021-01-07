package it.unisa.c03.myPersonalTrainer.account.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountDAOImplTest {


    @Test
    void findAccountByEmail() throws InterruptedException, ExecutionException, IOException {

        AccountDAO dao = new AccountDAOImpl();
        Account accountToSearch = dao.findAccountByEmail("email@testing.com");
        assertEquals("email@testing.com", accountToSearch.getEmail());
    }


    @Test
    void updatePassword() throws IOException, ExecutionException, InterruptedException {
        AccountDAO dao = new AccountDAOImpl();

        assertEquals(true, dao.updatePassword("email@testing.com","changedPassword56"));
    }



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
