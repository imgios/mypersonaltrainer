package it.unisa.c03.myPersonalTrainer.trainingplan.dao;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface TrainingPlanDAO {

    /**
     * This method add a new TrainingPlan
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
