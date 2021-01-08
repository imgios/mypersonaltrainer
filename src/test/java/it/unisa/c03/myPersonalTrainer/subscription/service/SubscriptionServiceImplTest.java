package it.unisa.c03.myPersonalTrainer.subscription.service;

import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class SubscriptionServiceImplTest {

    /*
    @Test
    void createSubscription() {
    }

    @Test
    void searchSubscriptionByEmail() {
    }

     */

    @Test
    void checkSubscriptionState() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com","2021-03-01","30"));
        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        assertEquals(1,subService.checkSubscriptionState("subs@looking.com"));

    }

    //va cambiata la data ogni volta, deve essere al massimo distante 10 giorni per essere in scadenza
    @Test
    void getExpiringSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
<<<<<<< HEAD
        Subscription s = new Subscription("subs@looking.com","2021-01-16","30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com","2021-01-16","30"));
=======
        Subscription s = new Subscription("subs@looking.com","2021-01-08","30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com","2021-01-08","30"));
>>>>>>> feature/VerifyAppointments


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getExpiringSubscriptions();

        assertEquals(listToReturn.size(),listToReturnService.size());

    }

    @Test
    void getExpiredSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
        Subscription s = new Subscription("subs@looking.com","2020-01-05","30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com","2020-01-05","30"));


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getExpiredSubscriptions();

        assertEquals(listToReturn.size(),listToReturnService.size());
    }

    @Test
    void getActiveSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
        Subscription s = new Subscription("subs@looking.com","2022-01-05","30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com","2022-01-05","30"));


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getActiveSubscriptions();

        assertEquals(listToReturn.size(),listToReturnService.size());
    }
}