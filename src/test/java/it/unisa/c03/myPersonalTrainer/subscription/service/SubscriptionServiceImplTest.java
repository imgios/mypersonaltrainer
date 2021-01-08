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
import static org.mockito.Mockito.*;

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
        Subscription s = new Subscription("marcosica99@libero.it", "2021-07-08", "30");

        SubscriptionDAO subDao = Mockito.mock(SubscriptionDAO.class);
        Mockito.when(subDao.getSubscriptionbyEmail(anyString())).thenReturn(s);
        SubscriptionService subService1 = new SubscriptionServiceImpl(subDao);
        SubscriptionService subService = Mockito.mock(SubscriptionServiceImpl.class);

        Subscription subscription = Mockito.mock(Subscription.class);
        Mockito.when(subscription.getSentNotification()).thenReturn(0);

        doNothing().when(subService).sendEmail((isA(String.class)), isA(Subscription.class));

        subService1.checkIfSent("marcosica99@libero.it");

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



   /* void sendEmail() throws EmailException {
        SubscriptionDAO subscriptionDAO = Mockito.mock(SubscriptionDAO.class);
        SubscriptionService pservice = Mockito.mock(SubscriptionServiceImpl.class);

        //doNothing().when(new SubscriptionServiceImpl(subscriptionDAO)).sendEmail(anyString(),any());

        verify(pservice,times(1)).sendEmail(anyString(),any());

        //verify(pservice).sendEmail(anyString(),any());


    }*/
}