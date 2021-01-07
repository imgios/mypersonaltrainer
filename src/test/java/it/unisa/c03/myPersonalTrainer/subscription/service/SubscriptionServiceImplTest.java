package it.unisa.c03.myPersonalTrainer.subscription.service;

import it.unisa.c03.myPersonalTrainer.parameters.dao.ParametersDAO;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersService;
import it.unisa.c03.myPersonalTrainer.parameters.service.ParametersServiceImpl;
import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

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
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com", "2021-03-01", "30"));
        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        assertEquals(1, subService.checkSubscriptionState("subs@looking.com"));
    }

    //va cambiata la data ogni volta, deve essere al massimo distante 10 giorni per essere in scadenza
    @Test
    void getExpiringSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
        Subscription s = new Subscription("subs@looking.com", "2021-01-16", "30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com", "2021-01-16", "30"));


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getExpiringSubscriptions();

        assertEquals(listToReturn.size(), listToReturnService.size());
    }

    @Test
    void getExpiredSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
        Subscription s = new Subscription("subs@looking.com", "2020-01-05", "30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com", "2020-01-05", "30"));


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getExpiredSubscriptions();

        assertEquals(listToReturn.size(), listToReturnService.size());
    }

    @Test
    void getActiveSubscriptions() throws InterruptedException, ExecutionException, IOException {
        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);

        ArrayList<Subscription> listToReturn = new ArrayList<>();
        Subscription s = new Subscription("subs@looking.com", "2022-01-05", "30");
        listToReturn.add(s);
        Mockito.when(subDao.getAllSubscriptions()).thenReturn(listToReturn);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com", "2022-01-05", "30"));


        SubscriptionService subService = new SubscriptionServiceImpl(subDao);
        ArrayList<Subscription> listToReturnService = subService.getActiveSubscriptions();

        assertEquals(listToReturn.size(), listToReturnService.size());
    }

    @Test
    void checkIfSent() throws EmailException, InterruptedException, ExecutionException, IOException {
        Subscription s = new Subscription("subs@looking.com", "2022-01-05", "30");

        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(new Subscription("subs@looking.com", "2021-03-01", "30"));
        SubscriptionService subService = new SubscriptionServiceImpl(subDao);


        /*Mockito.when(subService2.searchSubscriptionByEmail(anyString())).thenReturn(subscription);


        doNothing().when(subService2).sendEmail(isA(String.class), isA(Subscription.class));

*/

        /*
        Subscription subscription = new Subscription();
        subscription.setSentNotification(0);

        Mockito.when(subService1.searchSubscriptionByEmail(anyString())).thenReturn(subscription);

        assertEquals(0, subscription.getSentNotification());
        doNothing().when(subService1).sendEmail(isA(String.class), isA(Subscription.class));
        Mockito.when(subDao.updateSentNotification(anyString())).thenReturn(true);

        assertTrue(subService1.changeSentNotification(anyString()));*/
    }


    @Test
    void testUpdateInsert() throws IOException, ExecutionException, InterruptedException {

        SubscriptionDAO subscriptionDAO = Mockito.mock(SubscriptionDAO.class);
        SubscriptionService pservice = new SubscriptionServiceImpl(subscriptionDAO);

        Mockito.when(subscriptionDAO.updateSentNotification(any())).thenReturn(true);
        assertEquals(true, pservice.changeSentNotification(any()));
    }

    @Test
    void testUpdateInsertFalse() throws IOException, ExecutionException, InterruptedException {

        SubscriptionDAO subscriptionDAO = Mockito.mock(SubscriptionDAO.class);
        SubscriptionService pservice = new SubscriptionServiceImpl(subscriptionDAO);

        Mockito.when(subscriptionDAO.updateSentNotification(any())).thenReturn(false);
        assertEquals(false, pservice.changeSentNotification(any()));
    }



}