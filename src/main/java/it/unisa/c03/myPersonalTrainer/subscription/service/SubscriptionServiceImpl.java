package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.DAYS;

public class SubscriptionServiceImpl implements SubscriptionService {

    /**
     * @exclude
     * */
    private SubscriptionDAO subscriptionDAO;

    /**
     * @exclude
     * */
    public static final int TEN_DAYS = 10;
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
     * It only needs the customer email. Then it sets a standard price
     * and the expiration date, obtained by adding an extra month
     * to the registration date.
     * @param customerMail the customer mail
     */
    @Override
    public void createSubscription(String customerMail) throws IOException {

        // subscription registration
        Subscription subBean = new Subscription();
        subBean.setCustomerMail(customerMail);
        subBean.setPrice("30");

        //setting the date one month later
        LocalDate local = LocalDate.now();
        LocalDate oneMonthLater = local.plusMonths(1);
        subBean.setExpDate(oneMonthLater.toString());


        subscriptionDAO.insertSubscription(subBean);
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

    /**
     * This method check the state of a subscription.
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
}
