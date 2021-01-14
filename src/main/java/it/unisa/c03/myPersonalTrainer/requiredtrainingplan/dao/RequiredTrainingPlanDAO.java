package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface RequiredTrainingPlanDAO {

    /**
     * this function alerts if
     * the Training Plan's request
     * is successfully stored into the db.
     * @param requiredTrainingPlan Object
     * RequiredTrainingPlan.
     * @throws IOException
     * @return true if request will correctly store,
     * false instead.
     */
    boolean storeRequest(RequiredTrainingPlan requiredTrainingPlan)
            throws IOException;

    /** this function can search if the email of
     * the user that try to make a request of
     * TrainingPlan is just registered
     * into the RequiredTrainingPlan Document.
     * @param email to find the user into the db
     * @return the object if the email exists,
     * and "null" if the email isn't registered
     */
    RequiredTrainingPlan findAccountByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method changes the status of
     * a TrainingPlanRequest of an account.
     * @param email of the account
     * @param status updated
     * @return true after the change has taken place
     * false instead.
     */
    boolean updateRequest(String email, int status) //UpdateRequest
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method looks for the document id of an account
     * stored also in RequiredTrainingPlan.
     * @param email of the account stored in RequiredTrainingPlan.
     * @return the document Id.
     */
    String getAccountDocumentIdByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This method gets all requests.
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
     ArrayList<RequiredTrainingPlan> getAllRequests()
            throws IOException, ExecutionException, InterruptedException;


}
