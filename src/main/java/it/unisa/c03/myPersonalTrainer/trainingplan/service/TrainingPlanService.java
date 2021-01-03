package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TrainingPlanService {

    /**
     * This method asks to DAO TrainingPlans.
     * @param email of the customer
     * @return List of TrainingPlans
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public List<TrainingPlan> getTrainingPlans(String email) throws InterruptedException, ExecutionException, IOException;

    /**
     * This method checks TrainingPlan entries.
     * @param exercise
     * @param repetitions
     * @param series
     * @param recoveryTime
     * @return boolean
     * @throws IllegalArgumentException
     */
    boolean checkExercise(String exercise, String repetitions,
                          String series, String recoveryTime)
            throws IllegalArgumentException;

    /**
     * This method calls TrainingPlanDAO.
     * @param trainingPlan
     * @return boolean
     * @throws IOException
     */
    boolean createTrainingPlan(TrainingPlan trainingPlan) throws IOException;

}
