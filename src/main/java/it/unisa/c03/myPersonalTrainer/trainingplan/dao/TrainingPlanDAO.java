package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface TrainingPlanDAO {

    /**
     * This method extracts training plans into database.
     * @param email of the customer
     * @return List of customer's training plan.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    Collection<TrainingPlan> getTrainingPlansByEmail(String email)
            throws IOException, ExecutionException, InterruptedException;

    /**
     * This method add a new TrainingPlan.
     * @param tp
     * @return boolean
     * @throws IOException
     */
    boolean insertTrainingPlan(TrainingPlan tp)
            throws IOException;

    /**
     * This method delete a TrainingPlan.
     * @param email
     * @return boolean
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean deleteTrainingPlan(
            String email)
            throws IOException, ExecutionException, InterruptedException;


}