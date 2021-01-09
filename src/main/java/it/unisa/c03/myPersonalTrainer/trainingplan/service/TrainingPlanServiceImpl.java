package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAOImpl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Implementation of TrainingPlanService.
 */
public class TrainingPlanServiceImpl implements TrainingPlanService {

    /**
     * This method asks to DAO TrainingPlans.
     * @param email of the customer
     * @return List of TrainingPlans
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public List<TrainingPlan> getTrainingPlans(String email) throws InterruptedException, ExecutionException, IOException {
        TrainingPlanDAO trainingPlanList = new TrainingPlanDAOImpl();
        return (List<TrainingPlan>) trainingPlanList.getTrainingPlansByEmail(email);
    }

    /**
     * this is TrainingPlan
     */
    private TrainingPlanDAO trainingPlanDAO;

    public TrainingPlanServiceImpl() {

    }

    /**
     * .
     * @param trainingPlanDAO
     */
    public TrainingPlanServiceImpl(TrainingPlanDAO trainingPlanDAO) {
        this.trainingPlanDAO = trainingPlanDAO;
    }

    /**
     * This method checks TrainingPlan entries.
     * @param exercise
     * @param repetitions
     * @param series
     * @param recoveryTime
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean checkExercise(String exercise, String repetitions,
                                 String series, String recoveryTime)
            throws IllegalArgumentException {

        boolean result = false;

        final int MAX_LENGHT_EX = 25;
        final int MAX_LENGHT = 3;
        final int MIN_LENGHT_EX = 4;
        final int MIN_LENGHT = 1;

        if (!exercise.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("invalid exercise");
        } else if (exercise.length() < MIN_LENGHT_EX
                || exercise.length() > MAX_LENGHT_EX) {
            throw new IllegalArgumentException("invalid exercise length");
        } else if (!series.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid series format");
        } else if (series.length() < MIN_LENGHT
                || series.length() > MAX_LENGHT) {
            throw new IllegalArgumentException("invalid series length");
        } else if (!repetitions.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid repetitions format");
        } else if (repetitions.length() < MIN_LENGHT
                || repetitions.length() > MAX_LENGHT) {
            throw new IllegalArgumentException("invalid repetitions length");
        } else if (!recoveryTime.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid recoveryTime format");
        } else if (recoveryTime.length() < MIN_LENGHT
                || recoveryTime.length() > MAX_LENGHT) {
            throw new IllegalArgumentException("invalid recoveryTime length");
        } else {
            result = true;
        }

        return result;
    }

    /**
     * This method calls TrainingPlanDAO.
     * @param trainingPlan
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean createTrainingPlan(TrainingPlan trainingPlan)
            throws IOException {
        return trainingPlanDAO.insertTrainingPlan(trainingPlan);
    }
}
