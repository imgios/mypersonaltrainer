package it.unisa.c03.myPersonalTrainer.subscription.service;


import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAO;
import it.unisa.c03.myPersonalTrainer.subscription.dao.SubscriptionDAOImpl;

import java.io.IOException;
import java.time.LocalDate;
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
}
