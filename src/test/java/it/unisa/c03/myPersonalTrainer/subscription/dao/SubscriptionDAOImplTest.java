package it.unisa.c03.myPersonalTrainer.subscription.dao;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionDAOImplTest {
/*
    Subscription subTest;

    @BeforeEach
    void setUp() throws IOException {
        subTest = new Subscription("subs@looking.com","2021-03-01","30");
        DBConnection.getConnection().collection("Subscription").add(subTest);
    }


    @AfterEach
    void clean() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","subs@looking.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

    @AfterAll
    static void clean2() throws IOException, ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> lqds = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","subs@looking.com").get().get().getDocuments();

        for(QueryDocumentSnapshot document : lqds)
        {
            document.getReference().delete();
        }
    }

     */

    @Test
    void getSubscriptionbyEmail() throws InterruptedException, ExecutionException, IOException {

        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        Subscription subToSearch = subDao.getSubscriptionbyEmail("subs@looking.com");
        assertEquals("subs@looking.com", subToSearch.getCustomerMail());
    }

    @Test
    void getAllSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        ArrayList<Subscription> voidList = new ArrayList<>();
        ArrayList<Subscription> listToReturn = subDao.getAllSubscriptions();
        assertNotEquals(voidList.size(), listToReturn.size());
    }

    @Test
    void updateNotification() throws IOException, ExecutionException, InterruptedException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        assertEquals(true, subDao.updateSentNotification("subs@looking.com"));
    }

}