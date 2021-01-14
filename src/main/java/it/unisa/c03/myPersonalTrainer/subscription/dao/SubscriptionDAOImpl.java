package it.unisa.c03.myPersonalTrainer.subscription.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
     *
     * @param clientMail customer mail
     * @return the Subscription.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Subscription getSubscriptionbyEmail(
            String clientMail)
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the Subscription collection
        CollectionReference accounts = null;

        accounts = DBConnection.getConnection().collection(
                "Subscription");


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
            subscriptionBean.setSentNotification(
                    Integer.parseInt(String.valueOf(
                            document.get("sentNotification"))));
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

    /**
     * This DAO method looks for the document id of subscription.
     *
     * @param email of the account
     * @return the document Id
     */
    @Override
    public String getSubscriptionDocumentIdByEmail(
            String email)
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the account collection
        CollectionReference accounts = null;

        accounts = DBConnection.getConnection().collection("Subscription");

        // Create a query against the collection.
        Query query = accounts.whereEqualTo("customerMail", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        String id = "";

        for (DocumentSnapshot document
                : querySnapshot.get().getDocuments()) {
            id = id + document.getId();
        }
        return id;
    }


    /**
     * This DAO method marks that the client received the notification.
     *
     * @param email client's subscription mail.
     * @return true after the change has taken place
     */
    @Override
    public boolean updateSentNotification(String email)
            throws IOException, ExecutionException, InterruptedException {
        //find document id
        String id = getSubscriptionDocumentIdByEmail(email);
        // Update an existing document thanks to its id
        DocumentReference docRef = DBConnection.getConnection()
                .collection("Subscription").document(id);
        // Update password field
        ApiFuture<WriteResult> future = docRef.update("sentNotification", 1);
        return true;
    }
}
