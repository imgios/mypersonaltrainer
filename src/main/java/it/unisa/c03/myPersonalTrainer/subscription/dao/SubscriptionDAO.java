package it.unisa.c03.myPersonalTrainer.subscription.dao;

import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface SubscriptionDAO {

    /**
     * this method interact with database to insert the subscription.
     *
     * @param sub the subscription to insert into database
     * @throws IOException
     */
    void insertSubscription(Subscription sub) throws IOException;

    /**
     * This method searches in the database for a customer's
     * subscription, given his email.
     * @param clientMail customer mail
     * @return the Subscription.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    Subscription getSubscriptionbyEmail(String clientMail)
            throws IOException, ExecutionException, InterruptedException;
}
