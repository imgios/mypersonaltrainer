package it.unisa.c03.myPersonalTrainer.trainingplan.service;

import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;
import it.unisa.c03.myPersonalTrainer.trainingplan.dao.TrainingPlanDAO;

import java.io.IOException;

/**
 * javadoc.
 */
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private TrainingPlanDAO trainingPlanDAO;

    public TrainingPlanServiceImpl(TrainingPlanDAO trainingPlanDAO) {
        this.trainingPlanDAO = trainingPlanDAO;
    }

    /**
     * rigo.
     * @param exercise
     * @param repetitions
     * @param series
     * @param recoveryTime
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean checkExercise(String exercise, String repetitions, String series, String recoveryTime) throws IllegalArgumentException {

        boolean result = false;
         final int MAX_LENGHT = 3;

        if (!exercise.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("invalid exercise");
        } else if (exercise.length() < 4 || exercise.length() > 25) {
            throw new IllegalArgumentException("invalid exercise length");
        } else if (!series.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid series format");
        } else if (series.length() < 1 || series.length() > MAX_LENGHT) {
            throw new IllegalArgumentException("invalid series length");
        } else if (!repetitions.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid repetitions format");
        } else if (repetitions.length() < 1 || repetitions.length() > 3) {
            throw new IllegalArgumentException("invalid repetitions length");
        } else if (!recoveryTime.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("invalid recoveryTime format");
        } else if (recoveryTime.length() < 1 || recoveryTime.length() > 3) {
            throw new IllegalArgumentException("invalid recoveryTime length");
        } else {
            result = true;
        }

        return result;
    }

    @Override
    public boolean createTrainingPlan(TrainingPlan trainingPlan)
            throws IOException {
        return trainingPlanDAO.insertTrainingPlan(trainingPlan);
    }
}
