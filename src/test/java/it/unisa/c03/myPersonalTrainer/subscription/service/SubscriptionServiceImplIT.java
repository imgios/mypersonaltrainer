package it.unisa.c03.myPersonalTrainer.subscription.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.agenda.bean.Appointment;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAO;
import it.unisa.c03.myPersonalTrainer.agenda.dao.AgendaDAOImpl;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class SubscriptionServiceImplIT {

    @BeforeAll
    static void setUp() throws IOException {
        //Sub per test: get expiring
        Subscription sub4 = new Subscription("abbonamento@gmail.com","2021-01-19","30");
        DBConnection.getConnection().collection("Subscription").add(sub4);

        //Sub per test: get expired
        Subscription subScad = new Subscription("abbonamento@scaduto.com","2020-01-19","30");
        DBConnection.getConnection().collection("Subscription").add(subScad);

        //Sub per test: get active
        Subscription subAct = new Subscription("abbonamento@attivo.com","2020-01-13","30");
        DBConnection.getConnection().collection("Subscription").add(subAct);

        //Sub per test:SEND EMAIL
        Account user_test2 = new Account("nome_cambio_email", "cognome_cambioemail_email", "1212121212",
                "myptgps@gmail.com", "PasswordVecchia12",
                0);

        DBConnection.getConnection().collection("Account").add(user_test2);
        Subscription sub = new Subscription("myptgps@gmail.com","2021-01-13","30");
        DBConnection.getConnection().collection("Subscription").add(sub);

        Account user_test1 = new Account("nome_cambio_email", "cognome_cambioemail_email", "1212121212",
                "marco@libero.it", "PasswordVecchia12",
                0);

        DBConnection.getConnection().collection("Account").add(user_test1);
        Subscription sub2 = new Subscription("marco@libero.it","2021-01-13","30");
        sub.setSentNotification(1);
        DBConnection.getConnection().collection("Subscription").add(sub2);
    }

    @AfterAll
    static void cancella() throws IOException, ExecutionException, InterruptedException {

        //Sub per test: get expiring
        List<QueryDocumentSnapshot> list_sub4 = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","abbonamento@gmail.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_sub4)
        {
            document.getReference().delete();
        }
        //Sub per test: get expired
        List<QueryDocumentSnapshot> list_subExp = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","abbonamento@scaduto.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_subExp)
        {
            document.getReference().delete();
        }

        //Sub per test: get active
        List<QueryDocumentSnapshot> list_subAct = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","abbonamento@attivo.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_subAct)
        {
            document.getReference().delete();
        }

        //Sub per test:SEND EMAIL
        List<QueryDocumentSnapshot> list_account_3 = DBConnection.getConnection().collection("Account").whereEqualTo("email","myptgps@gmail.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_account_3)
        {
            document.getReference().delete();
        }

        List<QueryDocumentSnapshot> list_sub = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","myptgps@gmail.com").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_sub)
        {
            document.getReference().delete();
        }

        List<QueryDocumentSnapshot> list_account_2 = DBConnection.getConnection().collection("Account").whereEqualTo("email","marco@libero.it").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_account_2)
        {
            document.getReference().delete();
        }

        List<QueryDocumentSnapshot> list_sub2 = DBConnection.getConnection().collection("Subscription").whereEqualTo("customerMail","marco@libero.it").get().get().getDocuments();
        for(QueryDocumentSnapshot document : list_sub2)
        {
            document.getReference().delete();
        }

    }


    @Test
    void checkSubscriptionState1() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        assertEquals(1, subService.checkSubscriptionState("subs@looking.com"));
    }


    @Test
    void getExpiringSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();

        SubscriptionService subService = new SubscriptionServiceImpl(subDao);

        //il delete serve a simulare una prima connessione al db, altrimenti non funziona
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Appointment x = new Appointment();
        agendaDAO.deleteappointment(x);

        ArrayList<Subscription> listToReturnService = subService.getExpiringSubscriptions();

        assertEquals(Subscription.class, listToReturnService.get(0).getClass());

    }

    @Test
    void getExpiredSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();

        SubscriptionService subService = new SubscriptionServiceImpl(subDao);

        //il delete serve a simulare una prima connessione al db, altrimenti non funziona
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Appointment x = new Appointment();
        agendaDAO.deleteappointment(x);

        ArrayList<Subscription> listToReturnService = subService.getExpiredSubscriptions();

        assertEquals(Subscription.class, listToReturnService.get(0).getClass());

    }

    @Test
    void getActiveSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = new SubscriptionDAOImpl();

        SubscriptionService subService = new SubscriptionServiceImpl(subDao);

        //il delete serve a simulare una prima connessione al db, altrimenti non funziona
        AgendaDAO agendaDAO = new AgendaDAOImpl();
        Appointment x = new Appointment();
        agendaDAO.deleteappointment(x);

        ArrayList<Subscription> listToReturnService = subService.getActiveSubscriptions();

        assertEquals(Subscription.class, listToReturnService.get(0).getClass());

    }

    @Test
    void testUpdateInsert() throws IOException, ExecutionException, InterruptedException {

        SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
        SubscriptionService pservice = new SubscriptionServiceImpl(subscriptionDAO);

        assertEquals(true, pservice.changeSentNotification("abbonamento@gmail.com"));
    }



    @Test
    void checkIfSent() throws EmailException, InterruptedException, ExecutionException, IOException {

        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        SubscriptionService subService1 = new SubscriptionServiceImpl(subDao);

        subService1.checkIfSent("myptgps@gmail.com");
    }

    @Test
    void checkIfSent1() throws EmailException, InterruptedException, ExecutionException, IOException {

        SubscriptionDAO subDao = new SubscriptionDAOImpl();
        SubscriptionService subService1 = new SubscriptionServiceImpl(subDao);

        subService1.checkIfSent("marco@libero.it");
    }

}
