package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import it.unisa.c03.myPersonalTrainer.firebase.DBConnection;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import java.util.concurrent.ExecutionException;
import java.io.IOException;

public class RequiredTrainingPlanDAOImpl implements RequiredTrainingPlanDAO {

    /**
     * this function store the email of the account
     * into the RequiredTrainingPlan document.
     * @param requiredTrainingPlan Object that
     * describe the requiredTrainingPlan.
     * @throws IOException
     * @return
     */
    @Override
    public boolean storeRequest(RequiredTrainingPlan requiredTrainingPlan)
            throws IOException {

        boolean controllo;
        controllo = false;

        DBConnection.getConnection().collection("RequiredTrainingPlan")
                .add(requiredTrainingPlan);
        controllo = true;

        return controllo;
    }

    /**
     * this function can check if the email is into the
     * RequiredTrainingPlan document so this user has yet a TrainngPlan request.
     * @param email is the pk to find the user into the
     * RequiredTrainingPlan document.
     * @return the RequiredTrainingPlan given its mail.
     */
    @Override
    public RequiredTrainingPlan findAccountByEmail(String email)
            throws IOException, ExecutionException, InterruptedException {

        // Create a reference to the requestTrainingPlan collection
        CollectionReference requestedTrainingPlans = null;

        requestedTrainingPlans = DBConnection.getConnection()
                .collection("RequiredTrainingPlan");


        // Create a query against the collection.
        Query query = requestedTrainingPlans.whereEqualTo("email", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        //create Bean to return
        RequiredTrainingPlan requiredTrainingPlanBean =
                new RequiredTrainingPlan();

        for (DocumentSnapshot document
                : querySnapshot.get().getDocuments()) {

            requiredTrainingPlanBean.setEmail(
                    String.valueOf(document.get("email")));
            requiredTrainingPlanBean.setRequired(Integer.parseInt(
                    String.valueOf(document.get("required"))));
        }

        return requiredTrainingPlanBean;
    }

    /**
     * This DAO method changes the status of an account
     * into RequiredTrainingPlan.
     * @param email of the account
     * @param status updated
     * @return true after the change has taken place
     */
    @Override
    public boolean updateRequest(String email, int status) //updateRequest
            throws IOException, ExecutionException, InterruptedException {

        //find document id
        String id = getAccountDocumentIdByEmail(email);
        System.out.println(id);
        // Update an existing document thanks to its id
        DocumentReference docRef = DBConnection.getConnection()
                .collection("RequiredTrainingPlan").document(id);

        // Update password field
        ApiFuture<WriteResult> future = docRef.update("required", status);

        return true;
    }

    /**
     * This DAO method looks for the document id of an account.
     * @param email of the account
     * @return the document Id
     */
    @Override
    public String getAccountDocumentIdByEmail(String email)
            throws IOException, ExecutionException, InterruptedException {
        // Create a reference to the account collection
        CollectionReference requestedTrainingPlans = null;

        requestedTrainingPlans = DBConnection.getConnection()
                .collection("RequiredTrainingPlan");

        // Create a query against the collection.
        Query query = requestedTrainingPlans.whereEqualTo("email", email);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        String id = "";

        for (DocumentSnapshot document
                : querySnapshot.get().getDocuments()) {
            id = id + document.getId();
        }
        return id;
    }

}
