package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface SubscriptionService {
    /**
     * This service method register the subscription for a customer.
     * It only needs the customer email. Then it sets a standard price
     * and the expiration date, obtained by adding an extra month
     * to the registration date.
     * @param customerMail the customer mail
     */
    void createSubscription(String customerMail) throws IOException;

    /**
     * his method searches in the database for a customer's
     * subscription, given his email.
     * @param customerMail customer mail
     * @return the Subscription.
     */
    Subscription searchSubscriptionByEmail(String customerMail)
            throws InterruptedException, ExecutionException, IOException;
}
