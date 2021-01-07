package it.unisa.c03.myPersonalTrainer.subscription.dao;

import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;

import java.io.IOException;
import java.util.ArrayList;
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
     *
     * @param clientMail customer mail
     * @return the Subscription.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    Subscription getSubscriptionbyEmail(String clientMail)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This method retrieves all subscriptions saved in db.
     *
     * @return subscription list
     */
    ArrayList<Subscription> getAllSubscriptions()
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method looks for the document id of subscription.
     * @param email of the account
     * @return the document Id
     */
    String getSubscriptionDocumentIdByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method marks that the client received the notification.
     *
     * @param email client's subscription mail.
     * @return true after the change has taken place
     */
    boolean updateSentNotification(String email)
            throws IOException, ExecutionException, InterruptedException;
}
