package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface SubscriptionService {
    /**
     * This service method register the subscription for a customer.
     * @param sub the subscription to register
     */
    void createSubscription(Subscription sub) throws IOException;

    /**
     * his method searches in the database for a customer's
     * subscription, given his email.
     * @param customerMail customer mail
     * @return the Subscription.
     */
    Subscription searchSubscriptionByEmail(String customerMail)
            throws InterruptedException, ExecutionException, IOException;
}
