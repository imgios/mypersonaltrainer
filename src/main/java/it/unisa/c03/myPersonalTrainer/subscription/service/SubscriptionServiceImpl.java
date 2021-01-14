package it.unisa.c03.myPersonalTrainer.subscription.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.DAYS;

public class SubscriptionServiceImpl implements SubscriptionService {
    private static final int NOT_SENT = 0;
    private static final int PORT = 465;
    /**
     * @exclude
     */
    private SubscriptionDAO subscriptionDAO;

    /**
     * @exclude
     */
    public static final int TEN_DAYS = 10;

    /**
     * Service constructor.
     *
     * @param subscriptionDao is required, because is the DAO that
     *                        all the service methods will call.
     */
    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDao) {
        subscriptionDAO = subscriptionDao;
    }


    /**
     * This service method register the subscription for a customer.
     * It only needs the customer email. Then it sets a standard price
     * and the expiration date, obtained by adding an extra month
     * to the registration date.
     *
     * @param customerMail the customer mail
     */
    @Override
    public void createSubscription(String customerMail) throws IOException {

        // subscription registration
        Subscription subBean = new Subscription();
        subBean.setCustomerMail(customerMail);
        subBean.setPrice("30");
        subBean.setSentNotification(0);

        //setting the date one month later
        LocalDate local = LocalDate.now();
        LocalDate oneMonthLater = local.plusMonths(1);
        subBean.setExpDate(oneMonthLater.toString());
        subscriptionDAO.insertSubscription(subBean);
    }

    /**
     * This method searches in the database for a customer's
     * subscription, given his email.
     *
     * @param customerMail customer mail
     * @return the Subscription.
     */
    @Override
    public Subscription searchSubscriptionByEmail(String customerMail)
            throws InterruptedException, ExecutionException, IOException {
        return subscriptionDAO.getSubscriptionbyEmail(customerMail);
    }

    /**
     * This method check the state of a subscription.
     *
     * @param customerMail the mail of the subscription owner
     * @return 1 if the subscription is valid, 0 if
     * subscription expiring, -1 for expired subscription.
     */
    @Override
    public int checkSubscriptionState(String customerMail)
            throws InterruptedException, ExecutionException, IOException {
        Subscription sub = searchSubscriptionByEmail(customerMail);

        // get today date
        LocalDate oggi = LocalDate.now();
        // get subscription expDate
        LocalDate scadenza = LocalDate.parse(sub.getExpDate());

        // calucating difference
        long dayBetween = DAYS.between(oggi, scadenza);

        //      A : if difference is >= 10 return 1, state : attivo
        if (dayBetween >= TEN_DAYS) {
            return 1;
        } else if (dayBetween >= 0 && dayBetween < TEN_DAYS) {
            //      B : if difference is >= 0 and < 10 return 0,
            //      state : in scadenza
            return 0;
        } else if (dayBetween < 0) {
            //      A : if difference is < 0 return -1, state : scaduto
            return -1;
        }
        return -1;
    }

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
    @Override
    public void checkIfSent(String customerMail)
            throws InterruptedException,
            ExecutionException, IOException, EmailException {

        Subscription subscription =
                searchSubscriptionByEmail(customerMail);
        if (subscription.getSentNotification() == NOT_SENT) {
            sendEmail(customerMail, subscription);
            changeSentNotification(customerMail);
        }
    }

    /**
     * Send an email to Client, because his subscription is expiring.
     *
     * @param customerMail Account of user to send email.
     * @param subscription Account subscription.
     * @throws EmailException
     */
    @Override
    public void sendEmail(String customerMail,
                          Subscription subscription) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(PORT);
        email.setAuthenticator(new DefaultAuthenticator(
                "mypt.gps.is@gmail.com", "mypt2021"));
        email.setSSLOnConnect(true);
        email.setFrom("mypt.gps.is@gmail.com");
        email.setSubject("Abbonamento in Scadenza");
        email.setMsg("Salve " + customerMail
                + "\nLa contattiamo da myPersonalTrainer"
                + " per informarla che il suo "
                + "abbonamento è in scadenza\nLa data di "
                + "scadenza e' fissata al "
                + subscription.getExpDate() + " al costo di "
                + "" + subscription.getPrice()
                + " euro.\nSaluti.");
        email.addTo(customerMail);
        email.send();
    }


    /**
     * This service method shows all expiring Subscriptions.
     *
     * @return the list of expiring Subscriptions
     */
    @Override
    public ArrayList<Subscription> getExpiringSubscriptions()
            throws InterruptedException, ExecutionException, IOException {

        //create the list to return
        ArrayList<Subscription> listToReturn = new ArrayList<>();

        //call dao to get all subs
        ArrayList<Subscription> listDao = subscriptionDAO.getAllSubscriptions();

        //filter each sub and add to list only expiring subs
        for (Subscription s : listDao) {
            if (checkSubscriptionState(s.getCustomerMail()) == 0) {
                listToReturn.add(s);
            }
        }
        return listToReturn;
    }

    /**
     * This service method shows all expired Subscriptions.
     *
     * @return the list of expired Subscriptions
     */
    @Override
    public ArrayList<Subscription> getExpiredSubscriptions()
            throws InterruptedException, ExecutionException, IOException {
        //create the list to return
        ArrayList<Subscription> listToReturn = new ArrayList<>();

        //call dao to get all subs
        ArrayList<Subscription> listDao = subscriptionDAO.getAllSubscriptions();

        //filter each sub and add to list only expired subs
        for (Subscription s : listDao) {
            if (checkSubscriptionState(s.getCustomerMail()) == -1) {
                listToReturn.add(s);
            }
        }
        return listToReturn;
    }

    /**
     * This service method shows all active Subscriptions.
     *
     * @return the list of active Subscriptions
     */
    @Override
    public ArrayList<Subscription> getActiveSubscriptions()
            throws InterruptedException, ExecutionException, IOException {
        //create the list to return
        ArrayList<Subscription> listToReturn = new ArrayList<>();
        //call dao to get all subs
        ArrayList<Subscription> listDao = subscriptionDAO.getAllSubscriptions();
        //filter each sub and add to list only expired subs
        for (Subscription s : listDao) {
            if (checkSubscriptionState(s.getCustomerMail()) == 1) {
                listToReturn.add(s);
            }
        }
        return listToReturn;
    }

    @Override
    public boolean changeSentNotification(String email) throws IOException, ExecutionException, InterruptedException {
        return subscriptionDAO.updateSentNotification(email);
    }
}
