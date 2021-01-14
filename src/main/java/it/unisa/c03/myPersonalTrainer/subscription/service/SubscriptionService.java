package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface SubscriptionService {
    /**
     * This service method register the subscription for a customer.
     * It only needs the customer email. Then it sets a standard price
     * and the expiration date, obtained by adding an extra month
     * to the registration date.
     *
     * @param customerMail the customer mail
     */
    void createSubscription(String customerMail) throws IOException;

    /**
     * This method searches in the database for a customer's
     * subscription, given his email.
     *
     * @param customerMail customer mail
     * @return the Subscription.
     */
    Subscription searchSubscriptionByEmail(String customerMail)
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This method check the state of a subscription.
     *
     * @param customerMail the mail of the subscription owner
     * @return 1 if the subscription is valid, 0 if
     * subscription expiring, -1 for expired subscription.
     */
    int checkSubscriptionState(String customerMail)
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This service method shows all expiring Subscriptions.
     *
     * @return the list of expiring Subscriptions
     */
    ArrayList<Subscription> getExpiringSubscriptions()
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This service method shows all expired Subscriptions.
     *
     * @return the list of expired Subscriptions
     */
    ArrayList<Subscription> getExpiredSubscriptions()
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This service method shows all active Subscriptions.
     *
     * @return the list of active Subscriptions
     */
    ArrayList<Subscription> getActiveSubscriptions()
            throws InterruptedException, ExecutionException, IOException;


    /**
     * This service method changes value sentNotification of Subscription .
     *
     * @param email of the account
     * @return true if the change has taken place, false otherwise.
     */
    boolean changeSentNotification(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * Send an email to the customer if his subscription is expiring.
     *
     * @param customerMail Account of user to send email.
     * @param subscription Account subscription.
     * @throws EmailException
     */
    void sendEmail(String customerMail, Subscription subscription) throws EmailException;

    /**
     * This method is called only if the client's subscription
     * is expiring and check if the client is already notified.
     * if it has not been sent, this method send an email
     * to the customer that his subscription is expiring
     *
     * @param customerMail the client in session to notify.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     * @throws EmailException
     */
    void checkIfSent(String customerMail) throws InterruptedException, ExecutionException, IOException, EmailException;
}
