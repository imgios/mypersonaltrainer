package it.unisa.c03.myPersonalTrainer.requiredtrainingplan.service;

import it.unisa.c03.myPersonalTrainer.requiredtrainingplan.bean.RequiredTrainingPlan;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface RequiredTrainingPlanService {

    /**
     * This function give the possibility to register a
     * user and his first request of TrainigPlan into the system.
     * @param requiredTrainingPlan request into the db
     * @return true if is possibly to insert the
     * account after check of the credentials,
     * false when fails the email is just registered
     * @throws IOException //
     * @throws IllegalArgumentException //
     */
    boolean registerRequest(RequiredTrainingPlan requiredTrainingPlan)
            throws IOException, IllegalArgumentException,
            ExecutionException, InterruptedException;

    /**
     * This service method checks if an account exists in the RequiredTrainingPlan.
     * @param email referring to the account to search for
     * @return true if the account exists, false if not
     */
    boolean searchAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This method return the account given its mail.
     * @param email referring to the account to search for
     * @return the Account if it exists, null if not
     */
    RequiredTrainingPlan getAccountByEmail(String email)
            throws InterruptedException, ExecutionException, IOException;

    /**
     * This service method changes the status of an account.
     * @param email of the account
     * @param status updated
     * @return true after the change has taken place
     */
    boolean changeRequest(String email, int status)
            throws IOException, ExecutionException, InterruptedException;
}
