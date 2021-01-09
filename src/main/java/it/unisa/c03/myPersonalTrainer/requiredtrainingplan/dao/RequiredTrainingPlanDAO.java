package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.dao;
import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface RequiredTrainingPlanDAO {

    /**
     * this function save the Training Plan's
     * request into the db.
     * @param requiredTrainingPlan describe the user
     * @throws IOException
     * @return true if request will correctly store
     * false instead.
     */
    boolean storeRequest(RequiredTrainingPlan requiredTrainingPlan)
            throws IOException;

    /** this function can search if the email is
     * just registered into the db.
     * @param email is the pk to find the user into the db
     * @return if the email is in the parameters of
     * the user and if the email isn't registered "null"
     */
    RequiredTrainingPlan findAccountByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method changes the password of an account.
     * @param email of the account
     * @param status updated
     * @return true after the change has taken place
     */
    boolean updateRequest(String email, int status) //UpdateRequest
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This DAO method looks for the document id of an account.
     * @param email of the account
     * @return the document Id
     */
    String getAccountDocumentIdByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;
}
