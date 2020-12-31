package it.unisa.c03.myPersonalTrainer.subscription.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.subscription.bean.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SubscriptionDAOImpl implements SubscriptionDAO {
    /**
     * this method interact with database to insert the subscription.
     *
     * @param sub the subscription to insert into database
     * @throws IOException
     */
    @Override
    public void insertSubscription(Subscription sub) throws IOException {
        DBConnection.getConnection().collection(
                "Subscription").add(sub);
    }

    /**
     * This method searches in the database for a customer's
     * subscription, given his email.
     * @param clientMail customer mail
     * @return the Subscription.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Subscription getSubscriptionbyEmail(String clientMail)
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the Subscription collection
        CollectionReference accounts = null;

        accounts = DBConnection.getConnection().collection("Subscription");


        // Create a query against the collection.
        Query query = accounts.whereEqualTo("customerMail", clientMail);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return
        Subscription subscriptionBean = new Subscription();

        for (DocumentSnapshot document
                : querySnapshot.get().getDocuments()) {

            subscriptionBean.setCustomerMail(
                    String.valueOf(document.get("customerMail")));
            subscriptionBean.setExpDate(
                    String.valueOf(document.get("expDate")));
            subscriptionBean.setPrice(
                    String.valueOf(document.get("price")));
        }


        return subscriptionBean;
    }

    /**
     * This method retrieves all subscriptions saved in db.
     *
     * @return subscription list
     */
    @Override
    public ArrayList<Subscription> getAllSubscriptions()
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the Subscription collection
        CollectionReference subColl = null;

        subColl = DBConnection.getConnection().collection("Subscription");

        // Create a query against the collection.
        Query query = subColl.select("customerMail", "expDate", "price");

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create the list to return
        ArrayList<Subscription> list = new ArrayList<>();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Subscription s = document.toObject(Subscription.class);
            list.add(s);
        }

        return list;
    }
}
