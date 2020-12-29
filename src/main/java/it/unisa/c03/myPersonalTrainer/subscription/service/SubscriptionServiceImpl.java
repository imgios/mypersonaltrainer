package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SubscriptionServiceImpl implements SubscriptionService {

    /**
     * @exclude
     * */
    private SubscriptionDAO subscriptionDAO;
    /**
     * Service constructor.
     * @param subscriptionDao is required, because is the DAO that
     * all the service methods will call.
     * */
    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDao) {
        subscriptionDAO = subscriptionDao;
    }


    /**
     * This service method register the subscription for a customer.
     *
     * @param sub the subscription to register
     */
    @Override
    public void createSubscription(Subscription sub) throws IOException {
        subscriptionDAO.insertSubscription(sub);
    }

    /**
     * his method searches in the database for a customer's
     * subscription, given his email.
     * @param customerMail customer mail
     * @return the Subscription.
     */
    @Override
    public Subscription searchSubscriptionByEmail(String customerMail)
            throws InterruptedException, ExecutionException, IOException {
        return subscriptionDAO.getSubscriptionbyEmail(customerMail);
    }
}
